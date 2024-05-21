package inhatc.yulo.back.cameraURL.repository;

import inhatc.yulo.back.cameraURL.entity.CameraURL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CameraURLRepository extends JpaRepository<CameraURL, Integer> {
}
