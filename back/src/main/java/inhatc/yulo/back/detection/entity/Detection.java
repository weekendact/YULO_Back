package inhatc.yulo.back.detection.entity;

import inhatc.yulo.back.camera.entity.Camera;
import inhatc.yulo.back.model.entity.Model;
import inhatc.yulo.back.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "detection")
public class Detection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detection_id")
    private Long detectionId;

    @ManyToOne
    @JoinColumn(name = "camera_id", referencedColumnName = "camera_id")
    private Camera camera;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "model_id", referencedColumnName = "model_id")
    private Model model;

    @Column(name = "detection_date")
    private LocalDateTime DetectionDate;

    @Column(name = "detection_server_path")
    private String detectionServerPath;

}
