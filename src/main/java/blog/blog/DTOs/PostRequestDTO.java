package blog.blog.DTOs;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequestDTO {

    private String userId;
    private String title;
    private String content;
    private Set<String> tags; 
}