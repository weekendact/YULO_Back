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
        List<Object[]> detectionList =  detectionRepository.findDetectionsByUserId(detectionRequestDTO.getUserId());
        List<DetectionResponseDTO> detectionResponseDTOList = new ArrayList<>();

        detectionList
                .forEach(detection -> {
                    DetectionResponseDTO detectionResponseDTO = new DetectionResponseDTO();

                    detectionResponseDTO.setCameraName(detection[0].toString());
                    detectionResponseDTO.setModelId((Long) detection[1]);
                    detectionResponseDTO.setDetectionDate((LocalDateTime) detection[2]);
                    detectionResponseDTO.setDetectionServerPath(detection[3].toString());
                    detectionResponseDTO.setDetectionChecked((Boolean) detection[4]);
                    detectionResponseDTO.setDetectionId((Long) detection[5]);

                    detectionResponseDTOList.add(detectionResponseDTO);
                });
        return detectionResponseDTOList;
    }
}
