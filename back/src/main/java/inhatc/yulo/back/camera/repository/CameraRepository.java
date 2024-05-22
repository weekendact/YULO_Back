package inhatc.yulo.back.camera.repository;

import inhatc.yulo.back.camera.entity.Camera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CameraRepository extends JpaRepository<Camera, Long> {
    @Query("SELECT c FROM Camera c WHERE c.user.userId = :userId")
    List<Camera> findByUserId(@Param("userId") Long userId);
}
