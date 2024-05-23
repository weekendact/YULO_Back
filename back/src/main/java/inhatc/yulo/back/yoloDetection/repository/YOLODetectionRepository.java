package inhatc.yulo.back.yoloDetection.repository;

import inhatc.yulo.back.yoloDetection.entity.YOLODetection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface YOLODetectionRepository extends JpaRepository<YOLODetection, Long> {
    @Query("SELECT yd FROM YOLODetection yd JOIN yd.camera c WHERE c.user.userId = :userId AND c.cameraName = :cameraName")
    List<YOLODetection> findDetectionsByUserIdAndCameraName(@Param("userId") Long userId, @Param("cameraName") String cameraName);

    @Query("SELECT yd FROM YOLODetection yd WHERE yd.user.userId = :userId")
    List<YOLODetection> findAllByUserId(Long userId);

    @Query("SELECT c.cameraName, COUNT(yd.camera.cameraId) AS cnt " +
            "FROM YOLODetection yd " +
            "JOIN Camera c ON yd.camera.cameraId = c.cameraId " +
            "WHERE yd.user.userId = :userId " +
            "AND FUNCTION('DATE', yd.yoloDetectionDate) = CURRENT_DATE " +
            "GROUP BY c.cameraName " +
            "ORDER BY cnt DESC")
    List<Object[]> findCameraDetectionCountsByUserId(@Param("userId") Long userId);


}
