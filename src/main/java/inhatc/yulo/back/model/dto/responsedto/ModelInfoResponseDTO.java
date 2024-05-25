package inhatc.yulo.back.model.dto.responsedto;

import lombok.Data;

@Data
public class ModelInfoResponseDTO {
    private Long modelId;
    private String modelName;
    private Double modelAccuracy;
    private Long modelAllUser;
}
