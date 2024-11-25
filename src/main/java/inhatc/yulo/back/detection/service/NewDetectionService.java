package inhatc.yulo.back.detection.service;

import inhatc.yulo.back.detection.dto.Notification;
import inhatc.yulo.back.detection.dto.requestDTO.NewDetectionRequestDTO;
import inhatc.yulo.back.detection.repository.DetectionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.beans.Transient;

@Service
public class NewDetectionService {

    @Autowired
    private DetectionRepository detectionRepository;

    private final Sinks.Many<Notification> sink;

    public NewDetectionService() {
        this.sink = Sinks.many().multicast().onBackpressureBuffer();
    }

    public void emitNewDetectionEvent(Long userId, String message) {
        Notification notification = new Notification(message, userId);
        sink.tryEmitNext(notification);
    }

    public Flux<Notification> getNotificationsForUser(NewDetectionRequestDTO newDetectionRequestDTO) {
        return sink.asFlux()
                .filter(notification -> notification.getUserId().equals(newDetectionRequestDTO.getUserId()));
    }

    public void handleDetectionEvent(Long userId) {
        String message = generateMessage(userId);
        emitNewDetectionEvent(userId, message);
    }

    private String generateMessage(Long userId) {
        String message = "ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ";
        return message;
    }
}
