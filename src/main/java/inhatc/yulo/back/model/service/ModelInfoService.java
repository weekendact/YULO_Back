package inhatc.yulo.back.model.service;

import inhatc.yulo.back.model.dto.responsedto.ModelInfoResponseDTO;
import inhatc.yulo.back.model.entity.Model;
import inhatc.yulo.back.model.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModelInfoService {

    @Autowired
    private ModelRepository modelRepository;

    public List<ModelInfoResponseDTO> findModelInfoList() {
        List<Model> modelList = modelRepository.findAll();

        List<ModelInfoResponseDTO> modelInfoResponseDTOList = new ArrayList<>();

        for (Model model : modelList) {
            ModelInfoResponseDTO modelInfoResponseDTO = new ModelInfoResponseDTO();
            modelInfoResponseDTO.setModelName(model.getModelName());
            modelInfoResponseDTO.setModelAccuracy(model.getModelAccuracy());
            modelInfoResponseDTO.setModelId(model.getModelId());
            modelInfoResponseDTO.setModelAllUser(model.getModelAllUser());

            modelInfoResponseDTOList.add(modelInfoResponseDTO);
        }
        return modelInfoResponseDTOList;
    }
}
