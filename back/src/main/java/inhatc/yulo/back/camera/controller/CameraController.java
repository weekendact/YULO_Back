package inhatc.yulo.back.camera.controller;

import inhatc.yulo.back.camera.dto.requestdto.CameraNameRequestDTO;
import inhatc.yulo.back.camera.dto.requestdto.CameraTodayDetectionRequestDTO;
import inhatc.yulo.back.camera.service.CameraNameService;
import inhatc.yulo.back.camera.service.CameraTodayDetectionService;
import inhatc.yulo.back.resultdto.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("camera")
public class CameraController {

    @Autowired
    private CameraTodayDetectionService cameraTodayDetectionService;
    @Autowired
    private CameraNameService cameraNameService;

    @PostMapping("/todayDetection")
    public ResultDTO<?> cameraTodayDetectionList(@RequestBody CameraTodayDetectionRequestDTO cameraTodayDetectionRequestDTO) {
        return new ResultDTO<>().makeResult(HttpStatus.OK, "data",
                cameraTodayDetectionService.findCameraTodayDetection(cameraTodayDetectionRequestDTO), "data");
    }

    @PostMapping("/cameraName")
    public ResultDTO<?> cameraNameList(@RequestBody CameraNameRequestDTO cameraNameRequestDTO) {
        return new ResultDTO<>().makeResult(HttpStatus.OK, "data", cameraNameService.findCameraName(cameraNameRequestDTO), "data");
    }
}
