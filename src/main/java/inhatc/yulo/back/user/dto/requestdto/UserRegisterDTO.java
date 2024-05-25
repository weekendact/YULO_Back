package inhatc.yulo.back.user.dto.requestdto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegisterDTO {

    private String userName;

    private String userEmail;

    private String userPw;
}
