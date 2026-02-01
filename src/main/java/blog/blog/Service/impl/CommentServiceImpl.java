package blog.blog.Service.impl;

import blog.blog.DTOs.CommentRequestDTO;
import blog.blog.DTOs.CommentResponseDTO;
import blog.blog.Entities.Comment;
import blog.blog.Entities.Post;
import blog.blog.Entities.User;
import blog.blog.Repositories.CommentRepository;
import blog.blog.Repositories.PostRepository;
import blog.blog.Repositories.UserRepository;
import blog.blog.Service.CommentService;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepo;
    private final PostRepository postRepo;
    private final UserRepository userRepo;

    public CommentServiceImpl(CommentRepository commentRepo, PostRepository postRepo, UserRepository userRepo) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }

    @Override
    public CommentResponseDTO createComment(CommentRequestDTO dto) {

        Post post = postRepo.findById(dto.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setUser(user);
        comment.setCommentText(dto.getCommentText());
        comment.setCreatedAt(LocalDateTime.now());

        Comment saved = commentRepo.save(comment);
        return mapToResponse(saved);
    }

    @Override
    public List<CommentResponseDTO> getAllComments() {
        return commentRepo.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CommentResponseDTO getCommentById(UUID id) {
        Comment comment = commentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        return mapToResponse(comment);
    }

    @Override
    public CommentResponseDTO updateComment(UUID id, CommentRequestDTO dto) {

        Comment comment = commentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        comment.setCommentText(dto.getCommentText());

        Comment updated = commentRepo.save(comment);
        return mapToResponse(updated);
    }

    @Override
    public void deleteComment(UUID id) {
        Comment comment = commentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        commentRepo.delete(comment);
    }

    private CommentResponseDTO mapToResponse(Comment comment) {
        return CommentResponseDTO.builder()
                .id(comment.getId())
                .postId(comment.getPost().getId())
                .userId(comment.getUser().getId())
                .commentText(comment.getCommentText())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
