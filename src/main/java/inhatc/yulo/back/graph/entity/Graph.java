package inhatc.yulo.back.graph.entity;

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
@Table(name = "graph")
public class Graph {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "graph_id")
    private Long graphId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "camera_id")
    private Camera camera;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    @Column(name = "graph_date")
    private LocalDateTime graphDate;

    @Column(name = "graph_count")
    private Integer graphCount;

}
