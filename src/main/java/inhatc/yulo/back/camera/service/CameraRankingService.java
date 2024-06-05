package inhatc.yulo.back.camera.service;

import inhatc.yulo.back.camera.dto.requestdto.CameraRankingRequestDTO;
import inhatc.yulo.back.camera.repository.CameraRepository;
import inhatc.yulo.back.detection.repository.DetectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CameraRankingService {
    @Autowired
    private CameraRepository cameraRepository;

    @Autowired
    private DetectionRepository detectionRepository;

    public Map<String, Object> findCameraRanking(CameraRankingRequestDTO cameraRankingRequestDTO) {
        List<Object[]> detectionList = detectionRepository.findCameraDetectionCountsByUserId(cameraRankingRequestDTO.getUserId());

        if (detectionList.isEmpty()) {
            return Collections.singletonMap("", 0);
        }

        return detectionList.stream()
                .collect(Collectors.toMap(
                        detection -> (String) detection[0],
                        detection -> (Long) detection[1]
                ));
    }

}
