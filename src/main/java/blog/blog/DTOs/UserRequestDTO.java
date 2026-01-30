package blog.blog.DTOs;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    private String fullName;
    private String email;
    private String password;
    private String website;
    private String address;
    
}
