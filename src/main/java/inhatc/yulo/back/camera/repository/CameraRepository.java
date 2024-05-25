package inhatc.yulo.back.camera.repository;

import inhatc.yulo.back.camera.entity.Camera;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CameraRepository extends JpaRepository<Camera, Long> {
    @Query("SELECT c FROM Camera c WHERE c.user.userId = :userId")
    List<Camera> findByUserId(@Param("userId") Long userId);

    Optional<Camera> findByCameraId(Long cameraId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Camera c WHERE c.cameraName = :cameraName AND c.user.userId = :userId")
    void deleteCameraByCameraNameAndUserId(@Param("cameraName") String cameraName, @Param("userId") Long userId);
}
