package inhatc.yulo.back.detection.service;


import inhatc.yulo.back.detection.dto.requestDTO.DetectionGraphRequestDTO;
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


    public List<DetectionGraphResponseDTO> getDetection(DetectionGraphRequestDTO detectionGraphRequestDTO) {
        List<Detection> detectionList = detectionRepository.findDetectionsByUserIdAndCameraName(detectionGraphRequestDTO.getUserId(), detectionGraphRequestDTO.getCameraName());
        List<DetectionGraphResponseDTO> detectionGraphResponseDTOList = new ArrayList<>();

        detectionList
                .stream().toList()
                .forEach(detection -> {
                    DetectionGraphResponseDTO detectionGraphResponseDTO = new DetectionGraphResponseDTO();
                    detectionGraphResponseDTO.setDetectionDate(detection.getDetectionDate());
                    detectionGraphResponseDTO.setDetectionCount(null);

                    detectionGraphResponseDTOList.add(detectionGraphResponseDTO);
                });

        return detectionGraphResponseDTOList;
    }
}
