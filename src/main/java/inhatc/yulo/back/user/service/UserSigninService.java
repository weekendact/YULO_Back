package inhatc.yulo.back.user.service;

import inhatc.yulo.back.user.dto.requestdto.UserLoginDTO;
import inhatc.yulo.back.user.dto.requestdto.UserTokenLoginDTO;
import inhatc.yulo.back.user.entity.User;
import inhatc.yulo.back.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserSigninService {

    @Autowired
    private UserRepository userRepository;

    public Long findUser(UserLoginDTO userLoginDTO) {
        Optional<User> optionalUser = userRepository.findByUserEmail(userLoginDTO.getUserEmail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if(user.getUserPw().equals(userLoginDTO.getUserPw())) {
                return user.getUserId();
            }
        }
        return null;
    }

    public boolean findUser(UserTokenLoginDTO userTokenLoginDTO) {
        Optional<User> optionalUser = userRepository.findById(userTokenLoginDTO.getUserId());
        return optionalUser.isPresent();
    }
}