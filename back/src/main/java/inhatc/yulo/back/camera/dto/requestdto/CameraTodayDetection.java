package inhatc.yulo.back.camera.dto.requestdto;

import inhatc.yulo.back.user.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CameraTodayDetection {
    private Long userId;
    private String cameraName;
}
