package inhatc.yulo.back.camera.service;

import inhatc.yulo.back.camera.dto.requestdto.CameraRankingRequestDTO;
import inhatc.yulo.back.camera.entity.Camera;
import inhatc.yulo.back.camera.repository.CameraRepository;
import inhatc.yulo.back.yoloDetection.entity.YOLODetection;
import inhatc.yulo.back.yoloDetection.repository.YOLODetectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CameraRankingService {
    @Autowired
    private CameraRepository cameraRepository;

    @Autowired
    private YOLODetectionRepository yoloDetectionRepository;

    public Map<String, Object> findCameraRanking(CameraRankingRequestDTO cameraRankingRequestDTO) {
         List<Object []> yoloDetectionList = yoloDetectionRepository.findCameraDetectionCountsByUserId(cameraRankingRequestDTO.getUserId());
         Map<String, Object> resultMap = new HashMap<>();

         if (yoloDetectionList.isEmpty()) {
             return null;
         }

         for(Object[] yoloDetection : yoloDetectionList) {
             String cameraName = (String) yoloDetection[0];
             Long detectionCount = (Long) yoloDetection[1];

             resultMap.put(cameraName, detectionCount);
         }

        return resultMap;
    }


}
