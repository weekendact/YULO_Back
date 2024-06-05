package inhatc.yulo.back.camera.service;

import inhatc.yulo.back.camera.dto.requestdto.CameraSettingRequestDTO;
import inhatc.yulo.back.camera.dto.responsedto.CameraSettingResponseDTO;
import inhatc.yulo.back.camera.entity.Camera;
import inhatc.yulo.back.camera.repository.CameraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CameraSettingService {

    @Autowired
    private CameraRepository cameraRepository;

    public List<CameraSettingResponseDTO> cameraSettingList(CameraSettingRequestDTO cameraSettingRequestDTO) {
        List<Camera> cameraList = cameraRepository.findByUserId(cameraSettingRequestDTO.getUserId());
        // null 값으로 받았을 때 처리가 가능한지 확인
        if (cameraList.isEmpty()) return null;

        List<CameraSettingResponseDTO> cameraSettingResponseDTOList = new ArrayList<>();

        cameraList
                .forEach(camera -> {
                    CameraSettingResponseDTO cameraSettingResponseDTO = new CameraSettingResponseDTO();
                    cameraSettingResponseDTO.setCameraName(camera.getCameraName());
                    cameraSettingResponseDTO.setCameraURL(camera.getCameraURL());
                    cameraSettingResponseDTO.setCameraId(camera.getCameraId());
                    cameraSettingResponseDTO.setGraphURL(camera.getGraphURL());
                    cameraSettingResponseDTO.setStreamURL(camera.getStreamURL());
                    cameraSettingResponseDTO.setModelId(camera.getModel().getModelId());

                    cameraSettingResponseDTOList.add(cameraSettingResponseDTO);
                });

        return cameraSettingResponseDTOList;

    }
}
