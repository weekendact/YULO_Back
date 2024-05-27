package inhatc.yulo.back.camera.service;

import inhatc.yulo.back.camera.dto.requestdto.CameraUpdateRequestDTO;
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
public class CameraUpdateService {

    @Autowired
    private CameraRepository cameraRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelRepository modelRepository;

    public boolean updateCamera(CameraUpdateRequestDTO cameraUpdateRequestDTO) {
        Camera camera = cameraRepository.findByCameraId(cameraUpdateRequestDTO.getCameraId())
                .orElseThrow(() -> new EntityNotFoundException("Camera not found with id" + cameraUpdateRequestDTO.getCameraId()));

        User user = userRepository.findById(cameraUpdateRequestDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id" + cameraUpdateRequestDTO.getUserId()));
        camera.setUser(user);

        camera.setCameraName(cameraUpdateRequestDTO.getCameraName());
        camera.setLabel(cameraUpdateRequestDTO.getLabel());
        camera.setCount(cameraUpdateRequestDTO.getCount());
        Model model = modelRepository.findById(cameraUpdateRequestDTO.getModelId())
                .orElseThrow(() -> new EntityNotFoundException("Model not found with id: " + cameraUpdateRequestDTO.getModelId()));

        camera.setModel(model);
        camera.setCameraURL(cameraUpdateRequestDTO.getCameraURL());
        camera.setStreamURL(cameraUpdateRequestDTO.getStreamURL());
        camera.setGraphURL(cameraUpdateRequestDTO.getGraphURL());

        cameraRepository.save(camera);

        return true;

        }
    }

