package blog.blog.DTOs;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponseDTO {

    private UUID id;
    private UUID postId;
    private String  userId;

    private String commentText;
    private LocalDateTime createdAt;
}
