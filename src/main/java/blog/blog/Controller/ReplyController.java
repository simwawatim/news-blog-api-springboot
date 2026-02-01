package blog.blog.Controller;
import blog.blog.DTOs.ReplyRequestDTO;
import blog.blog.DTOs.ReplyResponseDTO;
import blog.blog.Response.ApiResponse;
import blog.blog.Service.ReplyService;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/replies/")
public class ReplyController {

    private final ReplyService service;

    private ReplyController(ReplyService service){
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<ReplyResponseDTO>> createReply(
            @RequestBody ReplyRequestDTO dto
    ) {
        return ResponseEntity.status(200).body(
                ApiResponse.<ReplyResponseDTO>builder()
                        .status("success")
                        .status_code(200)
                        .message("Reply created")
                        .data(service.createReply(dto))
                        .build()
        );
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<ReplyResponseDTO>>> getAllReplies() {
        return ResponseEntity.status(200).body(
                ApiResponse.<List<ReplyResponseDTO>>builder()
                        .status("success")
                        .status_code(200)
                        .message("Replies fetched")
                        .data(service.getAllReplies())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ReplyResponseDTO>> getReplyById(@PathVariable UUID id) {
        return ResponseEntity.status(200).body(
                ApiResponse.<ReplyResponseDTO>builder()
                        .status("success")
                        .status_code(200)
                        .message("Reply found")
                        .data(service.getReplyById(id))
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ReplyResponseDTO>> updateReply(
            @PathVariable UUID id,
            @RequestBody ReplyRequestDTO dto
    ) {
        return ResponseEntity.status(200).body(
                ApiResponse.<ReplyResponseDTO>builder()
                        .status("success")
                        .status_code(200)
                        .message("Reply updated successfully")
                        .data(service.updateReply(id, dto))
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteReply(@PathVariable UUID id) {

        service.deleteReply(id);

        return ResponseEntity.status(200).body(
                ApiResponse.<Void>builder()
                        .status("success")
                        .status_code(200)
                        .message("Reply deleted successfully")
                        .data(null)
                        .build()
        );
    }
}
