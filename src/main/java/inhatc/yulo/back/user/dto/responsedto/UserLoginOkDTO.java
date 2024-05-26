package inhatc.yulo.back.user.dto.responsedto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginOkDTO {
    private Long userId;
    private String userEmail;
}
