package inhatc.yulo.back.board.service;

import inhatc.yulo.back.board.dto.requestdto.UserInfoRequestDTO;
import inhatc.yulo.back.board.dto.responsedto.UserInfoResponseDTO;
import inhatc.yulo.back.user.entity.User;
import inhatc.yulo.back.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserRepository userRepository;

    public UserInfoResponseDTO getUserInfo(UserInfoRequestDTO userInfoRequestDTO) {
        User user = userRepository.findById(userInfoRequestDTO.getUserId())
                .orElseThrow( () -> new IllegalArgumentException("User not found"));

        return UserInfoResponseDTO.builder()
                .userName(user.getUserName())
                .userEmail(user.getUserEmail())
                .build();
    }
}
