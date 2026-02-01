package blog.blog.DTOs;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentRequestDTO {

    private UUID postId;
    private String userId;
    private String commentText;
}
