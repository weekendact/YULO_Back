package inhatc.yulo.back.yoloDetection.service;


import inhatc.yulo.back.yoloDetection.dto.requestdto.YOLODetectionDTO;
import inhatc.yulo.back.yoloDetection.dto.responsedto.YOLODetectionResponseDTO;
import inhatc.yulo.back.yoloDetection.entity.YOLODetection;
import inhatc.yulo.back.yoloDetection.repository.YOLODetectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class YOLODetectionService {

    @Autowired
    private YOLODetectionRepository yoloDetectionRepository;

    public List<YOLODetectionResponseDTO> getDetection(YOLODetectionDTO yoloDetectionDTO) {
        System.out.println("Received request for detection data. User ID: " + yoloDetectionDTO.getUserId() + ", Camera Name: " + yoloDetectionDTO.getCameraName());
        List<YOLODetection> detections = yoloDetectionRepository.findDetectionsByUserIdAndCameraName(yoloDetectionDTO.getUserId(), yoloDetectionDTO.getCameraName());
        List<YOLODetectionResponseDTO> detectionResponseDTOs = new ArrayList<>();

        System.out.println("Retrieved " + detections.size() + " detections for User ID: " + yoloDetectionDTO.getUserId() + " and Camera Name: " + yoloDetectionDTO.getCameraName());

        for(YOLODetection detection : detections) {
            YOLODetectionResponseDTO dto = new YOLODetectionResponseDTO();
            dto.setYoloDetectionDate(detection.getYoloDetectionDate());
            dto.setYoloDetectionCount(detection.getYoloDetectionCount());
            detectionResponseDTOs.add(dto);
            System.out.println("Created YOLODetectionResponseDTO: " + dto);
        }

        return detectionResponseDTOs;
    }
}
