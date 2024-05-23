package inhatc.yulo.back.camera.service;


import inhatc.yulo.back.camera.dto.requestdto.CameraTodayDetectionRequestDTO;
import inhatc.yulo.back.camera.dto.responsedto.CameraTodayDetectionResponseDTO;
import inhatc.yulo.back.camera.repository.CameraRepository;
import inhatc.yulo.back.detection.entity.Detection;
import inhatc.yulo.back.detection.repository.DetectionRepository;
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
    private DetectionRepository detectionRepository;

    public CameraTodayDetectionResponseDTO findCameraTodayDetection(CameraTodayDetectionRequestDTO cameraTodayDetectionRequestDTO) {
        List<Detection> detections = detectionRepository.findDetectionsByUserIdAndCameraName(
                cameraTodayDetectionRequestDTO.getUserId(), cameraTodayDetectionRequestDTO.getCameraName());

        LocalDate today = LocalDate.now(ZoneId.systemDefault());
        long detectionCount = 0;

        for (Detection detection : detections) {
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
