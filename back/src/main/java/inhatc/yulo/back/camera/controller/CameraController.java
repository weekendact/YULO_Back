package inhatc.yulo.back.camera.controller;

import inhatc.yulo.back.camera.dto.requestdto.CameraNameRequestDTO;
import inhatc.yulo.back.camera.dto.requestdto.CameraRankingRequestDTO;
import inhatc.yulo.back.camera.dto.requestdto.CameraSettingRequestDTO;
import inhatc.yulo.back.camera.dto.requestdto.CameraTodayDetectionRequestDTO;
import inhatc.yulo.back.camera.service.CameraNameService;
import inhatc.yulo.back.camera.service.CameraRankingService;
import inhatc.yulo.back.camera.service.CameraSettingService;
import inhatc.yulo.back.camera.service.CameraTodayDetectionService;
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
}
