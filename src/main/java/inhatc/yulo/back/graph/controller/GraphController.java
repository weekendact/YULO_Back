package inhatc.yulo.back.graph.controller;

import inhatc.yulo.back.graph.dto.requestDTO.GraphListRequestDTO;
import inhatc.yulo.back.graph.service.GraphListService;
import inhatc.yulo.back.resultdto.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("graph")
@CrossOrigin
public class GraphController {

    @Autowired
    private GraphListService graphListService;

    @PostMapping("/graphList")
    public ResultDTO<?> getGraphList(@RequestBody GraphListRequestDTO graphListRequestDTO) {
        return new ResultDTO<>().makeResult(HttpStatus.OK, "data", graphListService.findGraphList(graphListRequestDTO), "data");

    }

}
