package inhatc.yulo.back.yoloDetection.dto.responsedto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@ToString
public class YOLODetectionResponseDTO {
    private Date yoloDetectionDate;
    private Long yoloDetectionCount;
}
