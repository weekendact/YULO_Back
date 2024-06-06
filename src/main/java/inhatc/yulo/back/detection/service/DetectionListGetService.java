package inhatc.yulo.back.detection.service;

import inhatc.yulo.back.detection.dto.requestDTO.DetectionRequestDTO;
import inhatc.yulo.back.detection.dto.responseDTO.DetectionResponseDTO;
import inhatc.yulo.back.detection.repository.DetectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DetectionListGetService {

    @Autowired
    private DetectionRepository detectionRepository;

    public Page<DetectionResponseDTO> getDetectionList(Long userId, int page){
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize); // 페이지 번호는 0부터 시작

        Page<Object[]> detectionList =  detectionRepository.findDetectionsByUserId(userId, pageable);
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
        return new PageImpl<>(detectionResponseDTOList, pageable, detectionList.getTotalElements());
    }

    public List<DetectionResponseDTO> getDetectionList(DetectionRequestDTO detectionRequestDTO) {
        List<Object[]> detectionList = detectionRepository.findDetectionsByUserId(detectionRequestDTO.getUserId());
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
