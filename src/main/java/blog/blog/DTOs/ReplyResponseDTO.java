package blog.blog.DTOs;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyResponseDTO {

    private UUID id;
    private UUID commentId;
    private String userId;

    private String replyText;
    private LocalDateTime createdAt;
}
