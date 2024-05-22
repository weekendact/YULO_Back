package inhatc.yulo.back.camera.service;

import inhatc.yulo.back.camera.dto.requestdto.CameraRankingRequestDTO;
import inhatc.yulo.back.camera.dto.responsedto.CameraTodayDetectionResponseDTO;
import inhatc.yulo.back.camera.entity.Camera;
import inhatc.yulo.back.camera.repository.CameraRepository;
import inhatc.yulo.back.yoloDetection.dto.CameraDetectionCount;
import inhatc.yulo.back.yoloDetection.entity.YOLODetection;
import inhatc.yulo.back.yoloDetection.repository.YOLODetectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CameraRankingService {
    @Autowired
    private CameraRepository cameraRepository;

    @Autowired
    private YOLODetectionRepository yoloDetectionRepository;

    public Map<String, Object> findCameraRanking(CameraRankingRequestDTO cameraRankingRequestDTO) {
        List<CameraDetectionCount> cameraDetectionCountList = yoloDetectionRepository.findCameraDetectionCountsByUserId(cameraRankingRequestDTO.getUserId());
        if (cameraDetectionCountList.isEmpty())
            return null;

        List<CameraDetectionCount> sortedList = cameraDetectionCountList.stream()
                .sorted(Comparator.comparingLong(CameraDetectionCount::getDetectionCount).reversed())
                .toList();

        Map<String, Object> resultMap = new LinkedHashMap<>();

        for (CameraDetectionCount cameraDetectionCount : sortedList) {
            Optional<Camera> optionalCamera = cameraRepository.findByCameraId(cameraDetectionCount.getCameraId());
                Camera camera = optionalCamera.get();
                resultMap.put(camera.getCameraName(), cameraDetectionCount.getDetectionCount());
        }

        return resultMap;
    }


}
