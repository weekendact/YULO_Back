package inhatc.yulo.back.detection.dto.responseDTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DetectionResponseDTO {
    private Long modelId;
    private String cameraName;
    private LocalDateTime detectionDate;
    private String detectionServerPath;
    private Boolean detectionChecked;
    private Long detectionId;
}
