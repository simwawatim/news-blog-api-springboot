package blog.blog.DTOs;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyRequestDTO {

    private UUID commentId;
    private String userId;
    private String replyText;
}
