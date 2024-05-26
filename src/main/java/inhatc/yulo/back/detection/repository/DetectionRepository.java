package inhatc.yulo.back.detection.repository;

import inhatc.yulo.back.detection.entity.Detection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface DetectionRepository extends JpaRepository<Detection, Long> {
    @Query("SELECT d FROM Detection d JOIN d.camera c WHERE c.user.userId = :userId AND c.cameraName = :cameraName")
    List<Detection> findDetectionsByUserIdAndCameraName(Long userId, String cameraName);

    @Query("SELECT d FROM Detection d WHERE d.user.userId = :userId")
    List<Detection> findAllByUserId(Long userId);

    @Query("SELECT c.cameraName, COUNT(d.camera.cameraId) AS cnt " +
            "FROM Detection d " +
            "JOIN Camera c ON d.camera.cameraId = c.cameraId " +
            "WHERE d.user.userId = :userId " +
            "AND FUNCTION('DATE', d.detectionDate) = CURRENT_DATE " +
            "GROUP BY c.cameraName")
    List<Object[]> findCameraDetectionCountsByUserId(Long userId);

    @Query("SELECT c.cameraName, d.model.modelId, d.detectionDate, d.detectionServerPath, d.detectionChecked, d.detectionId " +
            "FROM Detection d " +
            "JOIN Camera c ON d.camera.cameraId = c.cameraId " +
            "WHERE d.user.userId = :userId " +
            "ORDER BY d.detectionDate DESC")
    List<Object[]> findDetectionsByUserId(Long userId);

    @Query("SELECT c.cameraName, d.detectionDate, d.model.modelId, d.detectionServerPath, d.detectionChecked, d.model.modelId " +
            "FROM Detection d " +
            "JOIN Camera c ON d.camera.cameraId = c.cameraId " +
            "WHERE d.user.userId = :userId " +
            "AND c.cameraName = :cameraName " +
            "AND d.detectionDate BETWEEN :startDate AND :endDate " +
            "ORDER BY d.detectionDate DESC")
    List<Object[]> findDetectionDetailsByUserIdAndCameraNameBetweenDates(Long userId,
                                                                         String cameraName,
                                                                         LocalDateTime startDate,
                                                                         LocalDateTime endDate);


    Optional<Detection> findByDetectionId(Long detectionId);


}
