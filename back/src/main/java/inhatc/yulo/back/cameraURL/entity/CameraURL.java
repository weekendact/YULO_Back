package inhatc.yulo.back.cameraURL.entity;

import inhatc.yulo.back.camera.entity.Camera;
import inhatc.yulo.back.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "camera_url")
public class CameraURL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "camera_url_id")
    private Long cameraURLId;

    @Column(name = "video_url")
    private String videoURL;

    @Column(name = "stream_url")
    private String streamURL;

    @Column(name = "graph_url")
    private String graphURL;

    @ManyToOne
    @JoinColumn(name = "camera_id", referencedColumnName = "camera_id")
    private Camera camera;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    

}
