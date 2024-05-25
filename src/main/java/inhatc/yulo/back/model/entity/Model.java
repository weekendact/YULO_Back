package inhatc.yulo.back.model.entity;

import inhatc.yulo.back.camera.entity.Camera;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "model")
public class Model {

    @Id
    @Column(name = "model_id")
    private Long modelId;

    @Column(name = "model_name")
    private String modelName;

    @Column(name = "model_accuracy")
    private Double modelAccuracy;

    @Column(name = "model_all_user")
    private Long modelAllUser;

}
