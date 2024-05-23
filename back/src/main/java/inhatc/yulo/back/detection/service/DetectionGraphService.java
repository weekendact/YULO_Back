package inhatc.yulo.back.detection.service;


import inhatc.yulo.back.detection.dto.requestDTO.DetectionGraphDTO;
import inhatc.yulo.back.detection.dto.responseDTO.DetectionGraphResponseDTO;
import inhatc.yulo.back.detection.entity.Detection;
import inhatc.yulo.back.detection.repository.DetectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class DetectionGraphService {

    @Autowired
    private DetectionRepository detectionRepository;


    public List<DetectionGraphResponseDTO> getDetection(DetectionGraphDTO detectionGraphDTO) {
        List<Detection> detections = detectionRepository.findDetectionsByUserIdAndCameraName(detectionGraphDTO.getUserId(), detectionGraphDTO.getCameraName());
        List<DetectionGraphResponseDTO> detectionGraphResponseDTOS = new ArrayList<>();


        for(Detection detection : detections) {
            DetectionGraphResponseDTO dto = new DetectionGraphResponseDTO();
            dto.setDetectionDate(detection.getDetectionDate());
            dto.setDetectionCount(null);
            detectionGraphResponseDTOS.add(dto);
        }

        return detectionGraphResponseDTOS;
    }
}
