package inhatc.yulo.back.camera.entity;

import inhatc.yulo.back.model.entity.Model;
import inhatc.yulo.back.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "camera")
public class Camera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "camera_id")
    private Long cameraId;

    @Column(name = "camera_name")
    private String cameraName;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "camera_url")
    private String cameraURL;

    @Column(name = "stream_url")
    private String streamURL;

    @Column(name = "graph_url")
    private String graphURL;

    @ManyToOne
    @JoinColumn(name = "model_id", referencedColumnName = "model_id")
    private Model model;

    @Column(name = "label")
    private Integer label;

    @Column(name = "count")
    private Integer count;
}
