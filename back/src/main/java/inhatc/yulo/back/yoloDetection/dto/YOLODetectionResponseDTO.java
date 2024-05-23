package inhatc.yulo.back.yoloDetection.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class YOLODetectionResponseDTO {
    private LocalDateTime yoloDetectionDate;
    private Long yoloDetectionCount;
}
