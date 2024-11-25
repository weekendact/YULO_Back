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
    private CameraNameService cameraNameService;
    @Autowired
    private CameraRankingService cameraRankingService;
    @Autowired
    private CameraSettingService cameraSettingService;
    @Autowired
    private CameraDeleteService cameraDeleteService;
    @Autowired
    private CameraAddService cameraAddService;
    @Autowired
    private CameraUpdateService cameraUpdateService;

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

    @PostMapping("/cameraAdd")
    public ResultDTO<?> camerAdd(@RequestBody CameraAddRequestDTO cameraAddRequestDTO) {
        return cameraAddService.addCamera(cameraAddRequestDTO)?
                new ResultDTO<>().makeResult(HttpStatus.OK, "data"):
                new ResultDTO<>().makeResult(HttpStatus.BAD_REQUEST, "이미 설정된 카메라 이름입니다.");
    }

    @PutMapping("/cameraUpdate")
    public ResultDTO<?> cameraUpdate(@RequestBody CameraUpdateRequestDTO cameraUpdateRequestDTO) {
        return cameraUpdateService.updateCamera(cameraUpdateRequestDTO)?
                new ResultDTO<>().makeResult(HttpStatus.OK, "data") :
                new ResultDTO<>().makeResult(HttpStatus.BAD_REQUEST, "카메라 수정 실패");
    }
}
