package inhatc.yulo.back.camera.controller;

import inhatc.yulo.back.camera.dto.requestdto.*;
import inhatc.yulo.back.camera.service.*;
import inhatc.yulo.back.resultdto.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("camera")
@CrossOrigin
public class CameraController {

    @Autowired
    private CameraTodayDetectionService cameraTodayDetectionService;
    @Autowired
    private CameraNameService cameraNameService;
    @Autowired
    private CameraRankingService cameraRankingService;
    @Autowired
    private CameraSettingService cameraSettingService;
    @Autowired
    private CameraDeleteService cameraDeleteService;

    @PostMapping("/todayDetection")
    public ResultDTO<?> cameraTodayDetectionList(@RequestBody CameraTodayDetectionRequestDTO cameraTodayDetectionRequestDTO) {
        return new ResultDTO<>().makeResult(HttpStatus.OK, "data",
                cameraTodayDetectionService.findCameraTodayDetection(cameraTodayDetectionRequestDTO), "data");
    }

    @PostMapping("/cameraName")
    public ResultDTO<?> cameraNameList(@RequestBody CameraNameRequestDTO cameraNameRequestDTO) {
        return new ResultDTO<>().makeResult(HttpStatus.OK, "data", cameraNameService.findCameraName(cameraNameRequestDTO), "data");
    }

    @PostMapping("/cameraRanking")
    public ResultDTO<?> cameraRankingList(@RequestBody CameraRankingRequestDTO cameraRankingRequestDTO) {
        return new ResultDTO<>().makeResult(HttpStatus.OK, "data", cameraRankingService.findCameraRanking(cameraRankingRequestDTO), "data");
    }

    @PostMapping("/cameraSetting")
    public ResultDTO<?> cameraSetting(@RequestBody CameraSettingRequestDTO cameraSettingRequestDTO) {
        return new ResultDTO<>().makeResult(HttpStatus.OK, "data", cameraSettingService.cameraSettingList(cameraSettingRequestDTO), "data");
    }

    @PostMapping("/cameraDelete")
    public ResultDTO<?> cameraDelete(@RequestBody CameraDeleteRequestDTO cameraDeleteRequestDTO) {
        cameraDeleteService.deleteCamera(cameraDeleteRequestDTO);
        return new ResultDTO<>().makeResult(HttpStatus.OK, "data");
    }
}
