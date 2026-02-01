package blog.blog.DTOs;


import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeRequestDTO {
    private UUID postId;
    private String userId;
}
