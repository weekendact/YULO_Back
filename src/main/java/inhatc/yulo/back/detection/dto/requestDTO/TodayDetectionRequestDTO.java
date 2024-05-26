package inhatc.yulo.back.detection.dto.requestDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TodayDetectionRequestDTO {
    private Long userId;
    private String cameraName;
}
