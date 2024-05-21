package inhatc.yulo.back.user.service;


import inhatc.yulo.back.jwt.JwtService;
import inhatc.yulo.back.user.dto.requestdto.UserLoginDTO;
import inhatc.yulo.back.user.dto.requestdto.UserRegisterDTO;
import inhatc.yulo.back.user.dto.responsedto.JwtResponseDTO;
import inhatc.yulo.back.user.entity.User;
import inhatc.yulo.back.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public JwtResponseDTO authenticateUser(UserLoginDTO userLoginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDTO.getUserEmail(), userLoginDTO.getUserPw())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateToken(userLoginDTO.getUserEmail());

        Long userId = userRepository.findByUserEmail(userLoginDTO.getUserEmail())
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getUserId();

        JwtResponseDTO jwtResponseDTO = new JwtResponseDTO(jwt, userId);

        return jwtResponseDTO;
    }

    public boolean registerUser(UserRegisterDTO userRegisterDTO) {
        if (userRepository.findByUserEmail(userRegisterDTO.getUserEmail()).isEmpty()) {
            User user = new User();

            user.setUserEmail(userRegisterDTO.getUserEmail());
            user.setUserPw(passwordEncoder.encode(userRegisterDTO.getUserPw()));
            user.setUserPhone(userRegisterDTO.getUserPhone());
            user.setUserBirthday(userRegisterDTO.getUserBirthday());
            user.setUserName(userRegisterDTO.getUserName());

            userRepository.save(user);
            return true;

        }
        return false;
    }
}
