package inhatc.yulo.back.detection.dto.responseDTO;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class DetectionsDetailsResponseDTO {
    private String cameraName;
    private LocalDateTime detectedDate;
    private Long modelId;
    private String detectionServerPath;
    private Boolean detectionChecked;
}
