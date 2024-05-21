package inhatc.yulo.back.jwt;

import java.util.Date;

import inhatc.yulo.back.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Builder
@AllArgsConstructor
@Data
public class JwtTokenDTO {
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private User user;
    private Date expiresIn;

    public JwtTokenDTO(String accessToken, String refreshToken, Date expiresIn, User user) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
        this.user = user;
    }
}
