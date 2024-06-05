package inhatc.yulo.back.detection.service;

import inhatc.yulo.back.detection.dto.requestDTO.DetectionsDetailsRequestDTO;
import inhatc.yulo.back.detection.dto.responseDTO.DetectionResponseDTO;
import inhatc.yulo.back.detection.repository.DetectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DetectionsDetailsService {

    @Autowired
    private DetectionRepository detectionRepository;

    public List<DetectionResponseDTO> findDetectionDetails(DetectionsDetailsRequestDTO detectionsDetailsRequestDTO) {
        List<Object[]> detectionDetailsList = detectionRepository.findDetectionDetailsByUserIdAndCameraNameBetweenDates(
                detectionsDetailsRequestDTO.getUserId(),
                detectionsDetailsRequestDTO.getCameraName(),
                detectionsDetailsRequestDTO.getStartDate(),
                detectionsDetailsRequestDTO.getEndDate());

        List<DetectionResponseDTO> detectionResponseDTOList = new ArrayList<>();

        detectionDetailsList
                .forEach(detectionDetails -> {
            DetectionResponseDTO detectionResponseDTO = new DetectionResponseDTO();

            detectionResponseDTO.setCameraName((String)detectionDetails[0]);
            detectionResponseDTO.setDetectionDate((LocalDateTime) detectionDetails[1]);
            detectionResponseDTO.setModelId((Long) detectionDetails[2]);
            detectionResponseDTO.setDetectionServerPath((String) detectionDetails[3]);
            detectionResponseDTO.setDetectionChecked((Boolean) detectionDetails[4]);
            detectionResponseDTO.setModelId((Long) detectionDetails[5]);

            detectionResponseDTOList.add(detectionResponseDTO);
        });

        return detectionResponseDTOList;
    }
}
