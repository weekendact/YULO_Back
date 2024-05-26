package inhatc.yulo.back.detection.service;

import inhatc.yulo.back.detection.dto.requestDTO.DetectionRequestDTO;
import inhatc.yulo.back.detection.dto.responseDTO.DetectionResponseDTO;
import inhatc.yulo.back.detection.repository.DetectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DetectionListGetService {

    @Autowired
    private DetectionRepository detectionRepository;

    public List<DetectionResponseDTO> getDetectionList(DetectionRequestDTO detectionRequestDTO){
        List<Object[]> getDetectionList =  detectionRepository.findDetectionsByUserId(detectionRequestDTO.getUserId());
        List<DetectionResponseDTO> detectionResponseDTOList = new ArrayList<>();

        for (Object[] objects : getDetectionList) {
            DetectionResponseDTO detectionResponseDTO = new DetectionResponseDTO();

            detectionResponseDTO.setCameraName(objects[0].toString());
            detectionResponseDTO.setModelId((Long) objects[1]);
            detectionResponseDTO.setDetectionDate((LocalDateTime) objects[2]);
            detectionResponseDTO.setDetectionServerPath(objects[3].toString());
            detectionResponseDTO.setDetectionChecked((Boolean) objects[4]);
            detectionResponseDTO.setDetectionId((Long) objects[5]);

            detectionResponseDTOList.add(detectionResponseDTO);
        }
        return detectionResponseDTOList;
    }
}
