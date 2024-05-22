package inhatc.yulo.back.yoloDetection.controller;

import inhatc.yulo.back.resultdto.ResultDTO;
import inhatc.yulo.back.yoloDetection.dto.requestdto.YOLODetectionDTO;
import inhatc.yulo.back.yoloDetection.dto.responsedto.YOLODetectionResponseDTO;
import inhatc.yulo.back.yoloDetection.service.YOLODetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("yolo/camera/detection")
public class YoloDetectionController {

    @Autowired
    private YOLODetectionService yoloDetectionService;

    @PostMapping("/getDetection")
    public ResultDTO<?> getDetection(@RequestBody YOLODetectionDTO yoloDetectionDTO) {
        List<YOLODetectionResponseDTO> detections = yoloDetectionService.getDetection(yoloDetectionDTO);
        return new ResultDTO<>().makeResult(HttpStatus.OK, "data", detections, "data");
    }

}
