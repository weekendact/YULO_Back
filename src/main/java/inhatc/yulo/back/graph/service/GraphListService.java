package inhatc.yulo.back.graph.service;

import inhatc.yulo.back.camera.repository.CameraRepository;
import inhatc.yulo.back.graph.dto.requestDTO.GraphListRequestDTO;
import inhatc.yulo.back.graph.dto.responseDTO.GraphListResponseDTO;
import inhatc.yulo.back.graph.entity.Graph;
import inhatc.yulo.back.graph.repository.GraphRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GraphListService {

    @Autowired
    private GraphRepository graphRepository;
    @Autowired
    private CameraRepository cameraRepository;

    public List<GraphListResponseDTO> findGraphList(GraphListRequestDTO graphListRequestDTO) {
        List<Graph> graphList = graphRepository.findGraphListByUserId(graphListRequestDTO.getUserId());
        List<String> cameraNameList = cameraRepository.findCameraNamesByUserId(graphListRequestDTO.getUserId());
        List<GraphListResponseDTO> graphListResponseDTOList = new ArrayList<>();

        for (String cameraName : cameraNameList) {
            GraphListResponseDTO graphListResponseDTO = new GraphListResponseDTO();
            graphListResponseDTO.setData(new ArrayList<>());
            graphListResponseDTO.setDateTime(new ArrayList<>());
            graphListResponseDTO.setName(cameraName);

            for (Graph graph : graphList) {
                if (graph.getCamera().getCameraName().equals(cameraName)) {
                    graphListResponseDTO.getData().add(graph.getGraphCount());
                    graphListResponseDTO.getDateTime().add(graph.getGraphDate());
                }
            }
            graphListResponseDTOList.add(graphListResponseDTO);
        }
        return graphListResponseDTOList;
    }
}
