package blog.blog.DTOs;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeResponseDTO {

    private UUID id;
    private UUID postId;
    private String userId;

    private LocalDateTime likedAt;
}
