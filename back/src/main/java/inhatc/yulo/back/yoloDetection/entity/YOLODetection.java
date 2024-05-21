package inhatc.yulo.back.yoloDetection.entity;

import inhatc.yulo.back.camera.entity.Camera;
import inhatc.yulo.back.model.entity.Model;
import inhatc.yulo.back.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "yolo_detection")
public class YOLODetection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "yolo_detection_id")
    private Long yoloDetectionId;

    @ManyToOne
    @JoinColumn(name = "camera_id", referencedColumnName = "camera_id")
    private Camera camera;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "model_id", referencedColumnName = "model_id")
    private Model model;

    @Column(name = "yolo_detection_date")
    private Date yoloDetectionDate;

    @Column(name = "yolo_detection_count")
    private Long yoloDetectionCount;



}
