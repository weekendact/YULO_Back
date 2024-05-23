package inhatc.yulo.back.camera.dto.requestdto;

import lombok.Data;

@Data
public class CameraDeleteRequestDTO {
    private Long userId;
    private String cameraName;
}
