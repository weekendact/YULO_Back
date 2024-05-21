package inhatc.yulo.back.user.dto.requestdto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginDTO {
    private String userEmail;

    private String userPw;
}
