package blog.blog.Service;

import blog.blog.DTOs.CommentRequestDTO;
import blog.blog.DTOs.CommentResponseDTO;

import java.util.List;
import java.util.UUID;

public interface CommentService {

    CommentResponseDTO createComment(CommentRequestDTO dto);

    List<CommentResponseDTO> getAllComments();

    CommentResponseDTO getCommentById(UUID id);

    CommentResponseDTO updateComment(UUID id, CommentRequestDTO dto);

    void deleteComment(UUID id);
}
