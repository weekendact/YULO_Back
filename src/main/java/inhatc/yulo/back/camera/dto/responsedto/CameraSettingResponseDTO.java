package inhatc.yulo.back.camera.dto.responsedto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CameraSettingResponseDTO {
    private String cameraName;
    private String graphURL;
    private String cameraURL;
    private String streamURL;
    private Long modelId;
}
