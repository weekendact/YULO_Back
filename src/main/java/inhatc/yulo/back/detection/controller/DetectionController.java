package inhatc.yulo.back.detection.controller;

import inhatc.yulo.back.detection.dto.requestDTO.*;
import inhatc.yulo.back.detection.dto.responseDTO.DetectionResponseDTO;
import inhatc.yulo.back.detection.service.*;
import inhatc.yulo.back.resultdto.ResultDTO;

import inhatc.yulo.back.detection.dto.responseDTO.DetectionGraphResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping ("/detections")
    public ResultDTO<?> detectionList(@RequestParam Long userId, @RequestParam(defaultValue = "1") int page) {
        Page<DetectionResponseDTO> detectionPage = detectionListGetService.getDetectionList(userId, page);

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("content", detectionPage.getContent());
        responseData.put("totalElements", detectionPage.getTotalElements()); // 전체 요소 수
        responseData.put("totalPages", detectionPage.getTotalPages()); // 전체 페이지 수
        responseData.put("number", detectionPage.getNumber()); // 현재 페이지 번호
        responseData.put("size", detectionPage.getSize()); // 페이지당 요소 수
        responseData.put("first", detectionPage.isFirst()); // 첫 번째 페이지인지 여부
        responseData.put("last", detectionPage.isLast()); // 마지막 페이지인지 여부

        return new ResultDTO<>().makeResult(HttpStatus.OK, "Detection list retrieved successfully", responseData, "data");
    }

    @PostMapping("/detectionsList")
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

    @PostMapping("/detectionDelete")
    public ResultDTO<?> detectionDelete(@RequestBody DetectionDeleteRequestDTO detectionDeleteRequestDTO) {
        boolean isDeleted = detectionListDeleteService.deleteDetectionList(detectionDeleteRequestDTO.getDetectionId());
        return isDeleted ?
                new ResultDTO<>().makeResult(HttpStatus.OK, "data") :
                new ResultDTO<>().makeResult(HttpStatus.BAD_REQUEST, "data");
    }
}
