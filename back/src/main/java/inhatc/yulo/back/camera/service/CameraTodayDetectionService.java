package inhatc.yulo.back.camera.service;


import inhatc.yulo.back.camera.dto.requestdto.CameraTodayDetection;
import inhatc.yulo.back.camera.dto.responsedto.CameraTodayDetectionDTO;
import inhatc.yulo.back.camera.repository.CameraRepository;
import inhatc.yulo.back.yoloDetection.entity.YOLODetection;
import inhatc.yulo.back.yoloDetection.repository.YOLODetectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CameraTodayDetectionService {

    @Autowired
    private CameraRepository cameraRepository;

    @Autowired
    private YOLODetectionRepository yoloDetectionRepository;

    public CameraTodayDetectionDTO findCameraTodayDetection(CameraTodayDetection cameraTodayDetection) {
        System.out.println(cameraTodayDetection.getUserId());
        System.out.println(cameraTodayDetection.getCameraName());
        List<YOLODetection> yoloDetections = yoloDetectionRepository.findDetectionsByUserIdAndCameraName(
                cameraTodayDetection.getUserId(), cameraTodayDetection.getCameraName());
        System.out.println(yoloDetections);
        if (!yoloDetections.isEmpty()) {
            CameraTodayDetectionDTO cameraTodayDetectionDTO = new CameraTodayDetectionDTO();

            cameraTodayDetectionDTO.setDetectionCount(yoloDetections.size());
            return cameraTodayDetectionDTO;
        }
        System.out.println("null");
        return null;
    }
}
