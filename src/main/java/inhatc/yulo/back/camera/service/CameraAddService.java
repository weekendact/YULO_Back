package inhatc.yulo.back.camera.service;

import inhatc.yulo.back.camera.dto.requestdto.CameraAddRequestDTO;
import inhatc.yulo.back.camera.entity.Camera;
import inhatc.yulo.back.camera.repository.CameraRepository;
import inhatc.yulo.back.model.entity.Model;
import inhatc.yulo.back.model.repository.ModelRepository;
import inhatc.yulo.back.user.entity.User;
import inhatc.yulo.back.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CameraAddService {

    @Autowired
    private CameraRepository cameraRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelRepository modelRepository;

    public void addCamera(CameraAddRequestDTO cameraAddRequestDTO) {
        Camera camera = new Camera();

        User user = userRepository.findById(cameraAddRequestDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + cameraAddRequestDTO.getUserId()));
        camera.setUser(user);

        camera.setCameraName(cameraAddRequestDTO.getCameraName());
        camera.setLabel(cameraAddRequestDTO.getLabel());
        camera.setCount(cameraAddRequestDTO.getCount());
        Model model = modelRepository.findById(cameraAddRequestDTO.getModelId())
                        .orElseThrow(() -> new EntityNotFoundException("Model not found with id : " + cameraAddRequestDTO.getModelId()));
        camera.setModel(model);
        camera.setCameraURL(cameraAddRequestDTO.getCameraURL());

        cameraRepository.save(camera);
    }
}
