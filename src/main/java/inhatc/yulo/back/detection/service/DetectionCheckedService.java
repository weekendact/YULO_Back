package inhatc.yulo.back.detection.service;

import inhatc.yulo.back.detection.dto.requestDTO.DetectionCheckedRequestDTO;
import inhatc.yulo.back.detection.dto.responseDTO.DetectionCheckedResponseDTO;
import inhatc.yulo.back.detection.entity.Detection;
import inhatc.yulo.back.detection.repository.DetectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DetectionCheckedService {

    @Autowired
    private DetectionRepository detectionRepository;

    public DetectionCheckedResponseDTO isDetectionChecked(DetectionCheckedRequestDTO detectionCheckedRequestDTO) {
        Optional<Detection> optionalDetection = detectionRepository.findByDetectionId(detectionCheckedRequestDTO.getDetectionId());

        if (optionalDetection.isPresent()) {

            Detection detection = optionalDetection.get();
            detection.setDetectionChecked(true);
            detectionRepository.save(detection);

            DetectionCheckedResponseDTO detectionCheckedResponseDTO = new DetectionCheckedResponseDTO();
            detectionCheckedResponseDTO.setDetectionChecked(true);
            return detectionCheckedResponseDTO;
        }
        return null;
    }
}
