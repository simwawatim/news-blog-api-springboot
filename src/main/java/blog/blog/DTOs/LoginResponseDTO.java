package blog.blog.DTOs;
import lombok.Data;

@Data
public class LoginResponseDTO {
    private String token;
    private String email;
    private String fullName;
}
