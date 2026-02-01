package blog.blog.Service;

import blog.blog.DTOs.ReplyRequestDTO;
import blog.blog.DTOs.ReplyResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ReplyService {

    ReplyResponseDTO createReply(ReplyRequestDTO dto);

    List<ReplyResponseDTO> getAllReplies();

    ReplyResponseDTO getReplyById(UUID id);

    ReplyResponseDTO updateReply(UUID id, ReplyRequestDTO dto);

    void deleteReply(UUID id);
}
