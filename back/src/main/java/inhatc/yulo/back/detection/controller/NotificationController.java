package inhatc.yulo.back.detection.controller;

import inhatc.yulo.back.detection.dto.Notification;
import inhatc.yulo.back.detection.dto.requestDTO.NewDetectionRequestDTO;
import inhatc.yulo.back.detection.service.DetectionGraphService;
import inhatc.yulo.back.detection.service.NewDetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    @Autowired
    private NewDetectionService newDetectionService;

    @Autowired
    private DetectionGraphService detectionGraphService;

    @PostMapping("/subscribe")
    public HttpStatus subscribeToNotifications(@RequestBody NewDetectionRequestDTO requestDTO) {
        return HttpStatus.OK;
    }

    @GetMapping(value = "/sse/notifications", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Notification> streamNotifications(@RequestBody NewDetectionRequestDTO newDetectionRequestDTO) {
        return newDetectionService.getNotificationsForUser(newDetectionRequestDTO);
    }
}
