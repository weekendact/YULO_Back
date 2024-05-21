package inhatc.yulo.back.user.controller;


import inhatc.yulo.back.jwt.JwtService;
import inhatc.yulo.back.user.dto.requestdto.UserLoginDTO;
import inhatc.yulo.back.user.dto.requestdto.UserRegisterDTO;
import inhatc.yulo.back.user.dto.responsedto.JwtResponseDTO;
import inhatc.yulo.back.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody UserLoginDTO userLoginDTO) {

        JwtResponseDTO response = userService.authenticateUser(userLoginDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterDTO userRegisterDTO) {

        return (userService.registerUser(userRegisterDTO))?
            ResponseEntity.ok("회원가입 성공"):
            ResponseEntity.badRequest().body("유저가 이미 있습니다.");

    }

}
