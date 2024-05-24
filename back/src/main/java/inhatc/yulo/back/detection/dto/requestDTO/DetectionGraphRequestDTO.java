package inhatc.yulo.back.detection.dto.requestDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class DetectionGraphRequestDTO {
    private Long userId;
    private String cameraName;

}
