package inhatc.yulo.back.user.controller;

import inhatc.yulo.back.resultdto.ResultDTO;
import inhatc.yulo.back.user.dto.requestdto.UserLoginDTO;
import inhatc.yulo.back.user.dto.requestdto.UserRegisterDTO;
import inhatc.yulo.back.user.service.UserSigninService;
import inhatc.yulo.back.user.service.UserSignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserSignupService userSignupService;
    @Autowired
    private UserSigninService userSigninService;

    @PostMapping("/Signup")
    public ResultDTO<?> userAdd(@RequestBody UserRegisterDTO userRegisterDTO) {
        return userSignupService.addUser(userRegisterDTO)?
                new ResultDTO<>().makeResult(HttpStatus.OK, "data"):
                new ResultDTO<>().makeResult(HttpStatus.BAD_REQUEST, "data");
    }

    @PostMapping("/Signin")
    public ResultDTO<?> userSave(@RequestBody UserLoginDTO userLoginDTO) {
        return new ResultDTO<>().makeResult(HttpStatus.OK, "data", userSigninService.findUser(userLoginDTO), "userId");
    }
}
