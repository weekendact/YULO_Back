package inhatc.yulo.back.camera.dto.requestdto;

import inhatc.yulo.back.model.entity.Model;
import inhatc.yulo.back.user.entity.User;
import lombok.Data;

@Data
public class CameraAddRequestDTO {
    private String cameraName;
    private Long userId;
    private String cameraURL;
    private Long modelId;
    private Integer label;
    private Integer count;
}
