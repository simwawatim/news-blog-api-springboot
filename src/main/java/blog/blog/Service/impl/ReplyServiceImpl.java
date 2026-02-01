package blog.blog.Service.impl;
import blog.blog.DTOs.ReplyRequestDTO;
import blog.blog.DTOs.ReplyResponseDTO;
import blog.blog.Entities.Comment;
import blog.blog.Entities.CommentReply;
import blog.blog.Entities.User;
import blog.blog.Repositories.CommentReplyRepository;
import blog.blog.Repositories.CommentRepository;
import blog.blog.Repositories.UserRepository;
import blog.blog.Service.ReplyService;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReplyServiceImpl implements ReplyService {

    private final CommentReplyRepository replyRepo;
    private final CommentRepository commentRepo;
    private final UserRepository userRepo;

    public ReplyServiceImpl(CommentReplyRepository replyRepo, CommentRepository commentRepo, UserRepository userRepo) {
        this.replyRepo = replyRepo;
        this.commentRepo = commentRepo;
        this.userRepo = userRepo;
    }

    @Override
    public ReplyResponseDTO createReply(ReplyRequestDTO dto) {

        Comment comment = commentRepo.findById(dto.getCommentId())
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        CommentReply reply = new CommentReply();
        reply.setComment(comment);
        reply.setUser(user);
        reply.setReplyText(dto.getReplyText());
        reply.setCreatedAt(LocalDateTime.now());

        CommentReply saved = replyRepo.save(reply);
        return mapToResponse(saved);
    }

    @Override
    public List<ReplyResponseDTO> getAllReplies() {
        return replyRepo.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ReplyResponseDTO getReplyById(UUID id) {
        CommentReply reply = replyRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Reply not found"));
        return mapToResponse(reply);
    }

    @Override
    public ReplyResponseDTO updateReply(UUID id, ReplyRequestDTO dto) {

        CommentReply reply = replyRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Reply not found"));

        reply.setReplyText(dto.getReplyText());

        CommentReply updated = replyRepo.save(reply);
        return mapToResponse(updated);
    }

    @Override
    public void deleteReply(UUID id) {

        CommentReply reply = replyRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Reply not found"));

        replyRepo.delete(reply);
    }

    private ReplyResponseDTO mapToResponse(CommentReply reply) {
        return ReplyResponseDTO.builder()
                .id(reply.getId())
                .commentId(reply.getComment().getId())
                .userId(reply.getUser().getId())
                .replyText(reply.getReplyText())
                .createdAt(reply.getCreatedAt())
                .build();
    }
}
