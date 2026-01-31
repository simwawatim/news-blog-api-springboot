package blog.blog.Service;

import blog.blog.DTOs.PostRequestDTO;
import blog.blog.DTOs.PostResponseDTO;
import java.util.List;
import java.util.UUID;
public interface PostService {

    List<PostResponseDTO> getAllPosts();
    PostResponseDTO createPost(PostRequestDTO dto);
    PostResponseDTO getPostById(UUID id);

    PostResponseDTO updatePost(UUID id, PostRequestDTO dto);

    void deletePost(UUID id);
    
}
