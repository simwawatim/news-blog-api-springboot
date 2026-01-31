package blog.blog.DTOs;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponseDTO {

    private UUID id;
    private String userId;
    private String fullName;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private Set<String> tags;
    private Integer readTime;
    private Integer likeCount;
    private Integer commentCount;
    private Integer shareCount;

    
}
