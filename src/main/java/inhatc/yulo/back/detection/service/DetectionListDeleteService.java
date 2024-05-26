package inhatc.yulo.back.detection.service;

import inhatc.yulo.back.detection.repository.DetectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetectionListDeleteService {

    @Autowired
    private DetectionRepository detectionRepository;

    public boolean deleteDetectionList(Long detectionId) {
        if(detectionRepository.existsById(detectionId)) {
            detectionRepository.deleteById(detectionId);
            return true;
        }
        return false;
    }
}
