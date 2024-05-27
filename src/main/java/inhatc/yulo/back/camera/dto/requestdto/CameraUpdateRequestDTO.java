package inhatc.yulo.back.camera.dto.requestdto;

import lombok.Data;

@Data
public class CameraUpdateRequestDTO {
    private Long cameraId;
    private String cameraName;
    private Long userId;
    private String cameraURL;
    private String streamURL;
    private String graphURL;
    private Long modelId;
    private Integer label;
    private Integer count;
}
