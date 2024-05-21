package inhatc.yulo.back.user.service;

import inhatc.yulo.back.user.dto.requestdto.UserRegisterDTO;
import inhatc.yulo.back.user.entity.User;
import inhatc.yulo.back.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserSignupService {

    @Autowired
    private UserRepository userRepository;

    public boolean addUser(UserRegisterDTO userRegisterDTO) {
        if (userRepository.findByUserEmail(userRegisterDTO.getUserEmail()).isEmpty()) {
            User user = new User();

            user.setUserEmail(userRegisterDTO.getUserEmail());
            user.setUserName(userRegisterDTO.getUserName());
            user.setUserPw(userRegisterDTO.getUserPw());

            userRepository.save(user);

            return true;
        } else {
            return false;
        }
    }
}
