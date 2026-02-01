package blog.blog.Service;


import java.util.UUID;

import blog.blog.DTOs.LikeRequestDTO;
import blog.blog.DTOs.LikeResponseDTO;

public interface LikeService {

    LikeResponseDTO likePost(LikeRequestDTO dto);

    String unlikePost(UUID postId, String userId);

    long countLikes(UUID postId);
}
