package inhatc.yulo.back.camera.controller;

import inhatc.yulo.back.camera.dto.requestdto.CameraTodayDetection;
import inhatc.yulo.back.camera.dto.responsedto.CameraTodayDetectionDTO;
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

    @PostMapping("/todayDetection")
    public ResultDTO<?> cameraTodayDetectionList(@RequestBody CameraTodayDetection cameraTodayDetection) {
        return new ResultDTO<>().makeResult(HttpStatus.OK, "data",
                cameraTodayDetectionService.findCameraTodayDetection(cameraTodayDetection), "data");
    }
}
