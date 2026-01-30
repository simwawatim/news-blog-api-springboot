package blog.blog.DTOs;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private String id;
    private String fullName;
    private String email;
    private String website;
    private String address;
    private int followersCount;
    private int followingCount;
}
