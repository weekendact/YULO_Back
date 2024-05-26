package inhatc.yulo.back.detection.service;

import inhatc.yulo.back.detection.dto.requestDTO.DetectionsDetailsRequestDTO;
import inhatc.yulo.back.detection.dto.responseDTO.DetectionsDetailsResponseDTO;
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

    public List<DetectionsDetailsResponseDTO> findDetectionDetails(DetectionsDetailsRequestDTO detectionsDetailsRequestDTO) {
        List<Object[]> detectionDetailsList = detectionRepository.findDetectionDetailsByUserIdAndCameraNameBetweenDates(
                detectionsDetailsRequestDTO.getUserId(), detectionsDetailsRequestDTO.getCameraName(), detectionsDetailsRequestDTO.getStartDate(), detectionsDetailsRequestDTO.getEndDate());

        List<DetectionsDetailsResponseDTO> detectionsDetailsResponseDTOList = new ArrayList<>();

        for (Object[] detectionDetails : detectionDetailsList) {
            DetectionsDetailsResponseDTO detectionsDetailsResponseDTO = new DetectionsDetailsResponseDTO();
            detectionsDetailsResponseDTO.setCameraName((String)detectionDetails[0]);
            detectionsDetailsResponseDTO.setDetectedDate((LocalDateTime) detectionDetails[1]);
            detectionsDetailsResponseDTO.setModelId((Long) detectionDetails[2]);
            detectionsDetailsResponseDTO.setDetectionServerPath((String) detectionDetails[3]);
            detectionsDetailsResponseDTO.setDetectionChecked((Boolean) detectionDetails[4]);

            detectionsDetailsResponseDTOList.add(detectionsDetailsResponseDTO);
        }
        return detectionsDetailsResponseDTOList;
    }
}
