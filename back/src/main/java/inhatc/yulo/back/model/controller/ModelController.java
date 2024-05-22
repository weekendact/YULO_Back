package inhatc.yulo.back.model.controller;

import inhatc.yulo.back.model.service.ModelInfoService;
import inhatc.yulo.back.resultdto.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("model")
public class ModelController {

    @Autowired
    private ModelInfoService modelInfoService;

    @PostMapping("/allInfoGet")
    public ResultDTO<?> modelInfoList() {
        return new ResultDTO<>().makeResult(HttpStatus.OK, "data", modelInfoService.findModelInfoList(), "data");
    }
}
