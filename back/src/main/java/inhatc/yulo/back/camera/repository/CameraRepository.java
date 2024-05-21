package inhatc.yulo.back.camera.repository;

import inhatc.yulo.back.camera.entity.Camera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CameraRepository extends JpaRepository<Camera, Long> {
}
