package inhatc.yulo.back.user.service;


import inhatc.yulo.back.user.dto.responsedto.UserSideBarDTO;
import inhatc.yulo.back.user.entity.User;
import inhatc.yulo.back.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserSideBarService {

    @Autowired
    private UserRepository userRepository;

    public UserSideBarDTO findUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        User user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        UserSideBarDTO userSideBarDTO = new UserSideBarDTO();
        userSideBarDTO.setUserEmail(user.getUserEmail());
        userSideBarDTO.setUserName(user.getUserName());

        return userSideBarDTO;
    }
}
