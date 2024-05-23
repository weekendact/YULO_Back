package inhatc.yulo.back.camera.service;

import inhatc.yulo.back.camera.dto.requestdto.CameraDeleteRequestDTO;
import inhatc.yulo.back.camera.repository.CameraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CameraDeleteService {

    @Autowired
    private CameraRepository cameraRepository;

    public void deleteCamera(CameraDeleteRequestDTO cameraDeleteRequestDTO) {
        cameraRepository.deleteCameraByCameraNameAndUserId(cameraDeleteRequestDTO.getCameraName(), cameraDeleteRequestDTO.getUserId());
    }
}
