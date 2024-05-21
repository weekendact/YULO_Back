package inhatc.yulo.back.user.dto.responsedto;

public class JwtResponseDTO {
    private String token;
    private Long userId;

    public JwtResponseDTO(String token, Long userId) {
        this.token = token;
        this.userId = userId;
    }

    // Getter and Setter
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}