package inhatc.yulo.back.detection.service;


import inhatc.yulo.back.detection.dto.requestDTO.TodayDetectionRequestDTO;
import inhatc.yulo.back.detection.dto.responseDTO.TodayDetectionResponseDTO;
import inhatc.yulo.back.detection.entity.Detection;
import inhatc.yulo.back.detection.repository.DetectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class TodayDetectionService {

    @Autowired
    private DetectionRepository detectionRepository;

    public TodayDetectionResponseDTO findCameraTodayDetection(TodayDetectionRequestDTO todayDetectionRequestDTO) {
        List<Detection> detections = detectionRepository.findDetectionsByUserIdAndCameraName(
                todayDetectionRequestDTO.getUserId(), todayDetectionRequestDTO.getCameraName());

        LocalDate today = LocalDate.now(ZoneId.systemDefault());
        long detectionCount = 0;

        for (Detection detection : detections) {
            if (detection.getDetectionDate().toLocalDate().isEqual(today)) {
                detectionCount++;
            }
        }

        if (detectionCount > 0) {
            TodayDetectionResponseDTO todayDetectionResponseDTO = new TodayDetectionResponseDTO();
            todayDetectionResponseDTO.setDetectionCount(detectionCount);
            return todayDetectionResponseDTO;
        }

        return null;
    }
}
