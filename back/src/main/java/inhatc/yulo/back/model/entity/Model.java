package inhatc.yulo.back.model.entity;

import inhatc.yulo.back.camera.entity.Camera;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

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
    private Long modelAccuracy;

    @ManyToOne
    @JoinColumn(name = "camera_id", referencedColumnName = "camera_id")
    private Camera camera;

}
