package inhatc.yulo.back.detection.controller;

import inhatc.yulo.back.resultdto.ResultDTO;
import inhatc.yulo.back.detection.dto.requestDTO.DetectionDTO;
import inhatc.yulo.back.detection.dto.responseDTO.DetectionResponseDTO;
import inhatc.yulo.back.detection.service.DetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("camera/detection")
@CrossOrigin
public class DetectionController {

    @Autowired
    private DetectionService detectionService;

    @PostMapping("/getDetection")
    public ResultDTO<?> getDetection(@RequestBody DetectionDTO detectionDTO) {
        List<DetectionResponseDTO> detections = detectionService.getDetection(detectionDTO);
        return new ResultDTO<>().makeResult(HttpStatus.OK, "data", detections, "data");
    }
}
