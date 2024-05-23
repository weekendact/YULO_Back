package inhatc.yulo.back.detection.repository;

import inhatc.yulo.back.detection.entity.Detection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DetectionRepository extends JpaRepository<Detection, Long> {
    @Query("SELECT d FROM Detection d JOIN d.camera c WHERE c.user.userId = :userId AND c.cameraName = :cameraName")
    List<Detection> findDetectionsByUserIdAndCameraName(@Param("userId") Long userId, @Param("cameraName") String cameraName);

    @Query("SELECT d FROM Detection d WHERE d.user.userId = :userId")
    List<Detection> findAllByUserId(Long userId);

    @Query("SELECT c.cameraName, COUNT(d.camera.cameraId) AS cnt " +
            "FROM Detection d " +
            "JOIN Camera c ON d.camera.cameraId = c.cameraId " +
            "WHERE d.user.userId = :userId " +
            "AND FUNCTION('DATE', d.DetectionDate) = CURRENT_DATE " +
            "GROUP BY c.cameraName")
    List<Object[]> findCameraDetectionCountsByUserId(@Param("userId") Long userId);

    @Query("SELECT c.cameraName, d.model.modelId, d.DetectionDate, d.detectionServerPath " +
            "FROM Detection d " +
            "JOIN Camera c ON d.camera.cameraId = c.cameraId " +
            "WHERE d.user.userId = :userId")
    List<Object[]> findDetectionByUserId(Long userId);


}
