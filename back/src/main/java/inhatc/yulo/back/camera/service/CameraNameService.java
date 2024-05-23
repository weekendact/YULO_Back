package inhatc.yulo.back.camera.service;

import inhatc.yulo.back.camera.dto.requestdto.CameraNameRequestDTO;
import inhatc.yulo.back.camera.entity.Camera;
import inhatc.yulo.back.camera.repository.CameraRepository;
import inhatc.yulo.back.detection.entity.Detection;
import inhatc.yulo.back.detection.repository.DetectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CameraNameService {

    @Autowired
    private CameraRepository cameraRepository;

    @Autowired
    private DetectionRepository detectionRepository;

    public Map<String, Integer> findCameraName(CameraNameRequestDTO cameraNameRequestDTO) {
        Map<String, Integer> resultMap = new HashMap<>();

        List<Camera> cameraList = cameraRepository.findByUserId(cameraNameRequestDTO.getUserId());

        int todayDetectionCount = 0;
        for (Camera camera : cameraList) {
            List<Detection> detections = detectionRepository.findDetectionsByUserIdAndCameraName(
                    cameraNameRequestDTO.getUserId(), camera.getCameraName());

            String cameraName = camera.getCameraName();
            int detectionCount = 0;

            LocalDate today = LocalDate.now(ZoneId.systemDefault());

            for (Detection detection : detections) {
                if (detection.getYoloDetectionDate().toLocalDate().isEqual(today)) {
                    detectionCount++;
                    todayDetectionCount++;
                }
            }

            resultMap.put(cameraName, detectionCount);
        }
        resultMap.put("todayDetection", todayDetectionCount);

        return resultMap;
    }
}
