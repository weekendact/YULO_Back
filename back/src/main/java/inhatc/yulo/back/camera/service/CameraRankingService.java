package inhatc.yulo.back.camera.service;

import inhatc.yulo.back.camera.dto.requestdto.CameraRankingRequestDTO;
import inhatc.yulo.back.camera.repository.CameraRepository;
import inhatc.yulo.back.detection.repository.DetectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CameraRankingService {
    @Autowired
    private CameraRepository cameraRepository;

    @Autowired
    private DetectionRepository detectionRepository;

    public Map<String, Object> findCameraRanking(CameraRankingRequestDTO cameraRankingRequestDTO) {
         List<Object []> DetectionList = detectionRepository.findCameraDetectionCountsByUserId(cameraRankingRequestDTO.getUserId());
         Map<String, Object> resultMap = new HashMap<>();

         if (DetectionList.isEmpty()) {
             resultMap.put("", 0);
             return resultMap;
         }

         for(Object[] detection : DetectionList) {
             String cameraName = (String) detection[0];
             Long detectionCount = (Long) detection[1];

             resultMap.put(cameraName, detectionCount);
         }

        return resultMap;
    }


}
