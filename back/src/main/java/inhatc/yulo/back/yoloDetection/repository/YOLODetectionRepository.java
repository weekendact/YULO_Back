package inhatc.yulo.back.yoloDetection.repository;

import inhatc.yulo.back.user.entity.User;
import inhatc.yulo.back.yoloDetection.entity.YOLODetection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YOLODetectionRepository extends JpaRepository<YOLODetection, Long> {
    @Query("SELECT yd FROM YOLODetection yd JOIN yd.camera c WHERE c.user.userId = :userId AND c.cameraName = :cameraName")
    List<YOLODetection> findDetectionsByUserIdAndCameraName(@Param("userId") Long userId, @Param("cameraName") String cameraName);

}

