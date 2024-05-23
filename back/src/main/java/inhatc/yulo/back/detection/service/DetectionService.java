package inhatc.yulo.back.detection.service;


import inhatc.yulo.back.detection.dto.Notification;
import inhatc.yulo.back.detection.dto.requestDTO.DetectionDTO;
import inhatc.yulo.back.detection.dto.responseDTO.DetectionResponseDTO;
import inhatc.yulo.back.detection.entity.Detection;
import inhatc.yulo.back.detection.repository.DetectionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.List;



@Service
public class DetectionService {

    @Autowired
    private DetectionRepository detectionRepository;

    private final Sinks.Many<Notification> sink;

    public DetectionService() {
        this.sink = Sinks.many().multicast().onBackpressureBuffer();
    }

    @Transactional
    public Detection saveDetection(Detection detection) {
        Detection savedDetection = detectionRepository.save(detection);
        sink.tryEmitNext(new Notification("새로운 감지 내역!: " + savedDetection.getDetectionDate().toString(), savedDetection.getDetectionCount()));
        return savedDetection;
    }

    public Flux<Notification> getNotifications() {
        return sink.asFlux();
    }

    public List<DetectionResponseDTO> getDetection(DetectionDTO detectionDTO) {
        List<Detection> detections = detectionRepository.findDetectionsByUserIdAndCameraName(detectionDTO.getUserId(), detectionDTO.getCameraName());
        List<DetectionResponseDTO> detectionResponseDTOs = new ArrayList<>();


        for(Detection detection : detections) {
            DetectionResponseDTO dto = new DetectionResponseDTO();
            dto.setDetectionDate(detection.getDetectionDate());
            dto.setDetectionCount(detection.getDetectionCount());
            detectionResponseDTOs.add(dto);
        }

        return detectionResponseDTOs;
    }
}
