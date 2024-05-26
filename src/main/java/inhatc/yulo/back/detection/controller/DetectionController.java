package inhatc.yulo.back.detection.controller;

import inhatc.yulo.back.detection.dto.requestDTO.*;
import inhatc.yulo.back.detection.service.*;
import inhatc.yulo.back.resultdto.ResultDTO;

import inhatc.yulo.back.detection.dto.responseDTO.DetectionGraphResponseDTO;
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
    @Autowired
    private DetectionCheckedService detectionCheckedService;
    @Autowired
    private TodayDetectionService todayDetectionService;
    @Autowired
    private DetectionsDetailsService detectionsDetailsService;
    @Autowired
    private DetectionListDeleteService detectionListDeleteService;

    @PostMapping("/getDetection")
    public ResultDTO<?> getDetection(@RequestBody DetectionGraphRequestDTO detectionGraphRequestDTO) {
        List<DetectionGraphResponseDTO> detections = detectionGraphService.getDetection(detectionGraphRequestDTO);
        return new ResultDTO<>().makeResult(HttpStatus.OK, "data", detections, "data");
    }

    @PostMapping("/detections")
    public ResultDTO<?> detectionList(@RequestBody DetectionRequestDTO detectionRequestDTO) {
        return new ResultDTO<>().makeResult(HttpStatus.OK, "data", detectionListGetService.getDetectionList(detectionRequestDTO), "data");
    }

    @PostMapping("/detectionCheck")
    public ResultDTO<?> detectionCheck(@RequestBody DetectionCheckedRequestDTO detectionCheckedRequestDTO) {
        return new ResultDTO<>().makeResult(HttpStatus.OK, "data", detectionCheckedService.isDetectionChecked(detectionCheckedRequestDTO), "data");
    }

    @PostMapping("/todayDetection")
    public ResultDTO<?> todayDetectionList(@RequestBody TodayDetectionRequestDTO todayDetectionRequestDTO) {
        return new ResultDTO<>().makeResult(HttpStatus.OK, "data",
                todayDetectionService.findCameraTodayDetection(todayDetectionRequestDTO), "data");
    }

    @PostMapping("/detectionsDetails")
    public ResultDTO<?> detectionsDetailsList(@RequestBody DetectionsDetailsRequestDTO detectionsDetailsRequestDTO) {
        return new ResultDTO<>().makeResult(HttpStatus.OK, "data", detectionsDetailsService.findDetectionDetails(detectionsDetailsRequestDTO), "data");
    }

    @PostMapping("/test-saveDetection")
    public ResultDTO<?> testSaveDetection(@RequestBody DetectionGraphRequestDTO detectionGraphRequestDTO) {return null;}

    @PostMapping("/detectionListDelete")
    public ResultDTO<?> detectionListDelete(@RequestBody DetectionDeleteRequestDTO detectionDeleteRequestDTO) {
        boolean isDeleted = detectionListDeleteService.deleteDetectionList(detectionDeleteRequestDTO.getDetectionId());
        return isDeleted ?
                new ResultDTO<>().makeResult(HttpStatus.OK, "data") :
                new ResultDTO<>().makeResult(HttpStatus.BAD_REQUEST, "data");
    }
}
