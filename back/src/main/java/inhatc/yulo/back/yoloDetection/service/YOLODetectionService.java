package inhatc.yulo.back.yoloDetection.service;


import inhatc.yulo.back.yoloDetection.dto.YOLODetectionDTO;
import inhatc.yulo.back.yoloDetection.dto.YOLODetectionResponseDTO;
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
        List<YOLODetection> detections = yoloDetectionRepository.findDetectionsByUserIdAndCameraName(yoloDetectionDTO.getUserId(), yoloDetectionDTO.getCameraName());
        List<YOLODetectionResponseDTO> detectionResponseDTOs = new ArrayList<>();


        for(YOLODetection detection : detections) {
            YOLODetectionResponseDTO dto = new YOLODetectionResponseDTO();
            dto.setYoloDetectionDate(detection.getYoloDetectionDate());
            dto.setYoloDetectionCount(detection.getYoloDetectionCount());
            detectionResponseDTOs.add(dto);
        }

        return detectionResponseDTOs;
    }
}
