package inhatc.yulo.back.user.controller;

import inhatc.yulo.back.resultdto.ResultDTO;
import inhatc.yulo.back.user.service.UserSideBarService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserSideBarService userSideBarService;

    @PostMapping("/userSideBar")
    public ResultDTO<?> userSideBar() {
        return new ResultDTO<>().makeResult(HttpStatus.OK, "User Info", userSideBarService.findUserInfo(), "userInfo");
    }
}
