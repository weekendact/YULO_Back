package inhatc.yulo.back.detection.controller;

import inhatc.yulo.back.detection.dto.Notification;
import inhatc.yulo.back.resultdto.ResultDTO;
import inhatc.yulo.back.detection.dto.requestDTO.DetectionDTO;
import inhatc.yulo.back.detection.dto.responseDTO.DetectionResponseDTO;
import inhatc.yulo.back.detection.service.YOLODetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("camera/detection")
@CrossOrigin
public class DetectionController {

    @Autowired
    private YOLODetectionService yoloDetectionService;

    @PostMapping("/getDetection")
    public ResultDTO<?> getDetection(@RequestBody DetectionDTO detectionDTO) {
        List<DetectionResponseDTO> detections = yoloDetectionService.getDetection(detectionDTO);
        return new ResultDTO<>().makeResult(HttpStatus.OK, "data", detections, "data");
    }

    @GetMapping(value = "/sse/notifications", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Notification> streamNotifications() {
        return yoloDetectionService.getNotifications();
    }

}
