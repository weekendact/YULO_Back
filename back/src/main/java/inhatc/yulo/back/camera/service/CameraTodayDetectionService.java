package inhatc.yulo.back.camera.service;


import inhatc.yulo.back.camera.dto.requestdto.CameraTodayDetectionRequestDTO;
import inhatc.yulo.back.camera.dto.responsedto.CameraTodayDetectionResponseDTO;
import inhatc.yulo.back.camera.repository.CameraRepository;
import inhatc.yulo.back.yoloDetection.entity.YOLODetection;
import inhatc.yulo.back.yoloDetection.repository.YOLODetectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class CameraTodayDetectionService {

    @Autowired
    private CameraRepository cameraRepository;

    @Autowired
    private YOLODetectionRepository yoloDetectionRepository;

    public CameraTodayDetectionResponseDTO findCameraTodayDetection(CameraTodayDetectionRequestDTO cameraTodayDetectionRequestDTO) {
        List<YOLODetection> yoloDetections = yoloDetectionRepository.findDetectionsByUserIdAndCameraName(
                cameraTodayDetectionRequestDTO.getUserId(), cameraTodayDetectionRequestDTO.getCameraName());

        LocalDate today = LocalDate.now(ZoneId.systemDefault());
        long detectionCount = 0;

        for (YOLODetection detection : yoloDetections) {
            if (detection.getYoloDetectionDate().toLocalDate().isEqual(today)) {
                detectionCount++;
            }
        }

        if (detectionCount > 0) {
            CameraTodayDetectionResponseDTO cameraTodayDetectionResponseDTO = new CameraTodayDetectionResponseDTO();
            cameraTodayDetectionResponseDTO.setDetectionCount(detectionCount);
            return cameraTodayDetectionResponseDTO;
        }

        return null;
    }
}
