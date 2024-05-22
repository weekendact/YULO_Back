package inhatc.yulo.back.yoloDetection.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraDetectionCount {
    private Long cameraId;
    private Long detectionCount;
}
