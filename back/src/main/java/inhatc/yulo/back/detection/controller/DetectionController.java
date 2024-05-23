package inhatc.yulo.back.detection.controller;

import inhatc.yulo.back.detection.dto.requestDTO.DetectionRequestDTO;
import inhatc.yulo.back.detection.service.DetectionListGetService;
import inhatc.yulo.back.resultdto.ResultDTO;
import inhatc.yulo.back.detection.dto.requestDTO.DetectionGraphDTO;
import inhatc.yulo.back.detection.dto.responseDTO.DetectionGraphResponseDTO;
import inhatc.yulo.back.detection.service.DetectionGraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("detection")
@CrossOrigin
public class DetectionController {

    @Autowired
    private DetectionGraphService detectionGraphService;
    @Autowired
    private DetectionListGetService detectionListGetService;


    @PostMapping("/getDetection")
    public ResultDTO<?> getDetection(@RequestBody DetectionGraphDTO detectionGraphDTO) {
        List<DetectionGraphResponseDTO> detections = detectionGraphService.getDetection(detectionGraphDTO);
        return new ResultDTO<>().makeResult(HttpStatus.OK, "data", detections, "data");
    }

    @PostMapping("/detections")
    public ResultDTO<?> detectionList(@RequestBody DetectionRequestDTO detectionRequestDTO) {
        return new ResultDTO<>().makeResult(HttpStatus.OK, "data", detectionListGetService.getDetectionList(detectionRequestDTO), "data");
    }

    @PostMapping("/test-saveDetection")
    public ResultDTO<?> testSaveDetection(@RequestBody DetectionGraphDTO detectionGraphDTO) {return null;}
}
