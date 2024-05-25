package inhatc.yulo.back.camera.dto.requestdto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CameraTodayDetectionRequestDTO {
    private Long userId;
    private String cameraName;
}
