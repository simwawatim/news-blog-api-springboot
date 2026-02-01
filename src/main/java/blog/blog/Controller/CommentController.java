package blog.blog.Controller;
import blog.blog.DTOs.CommentRequestDTO;
import blog.blog.DTOs.CommentResponseDTO;
import blog.blog.Response.ApiResponse;
import blog.blog.Service.CommentService;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments/")
public class CommentController {

    private final CommentService service;

    private CommentController(CommentService service){
        this.service = service;
    }


    @PostMapping()
    public ResponseEntity<ApiResponse<CommentResponseDTO>> createComment(
            @RequestBody CommentRequestDTO dto
    ) {
        return ResponseEntity.status(200).body(
                ApiResponse.<CommentResponseDTO>builder()
                        .status("success")
                        .status_code(200)
                        .message("Comment created")
                        .data(service.createComment(dto))
                        .build()
        );
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<CommentResponseDTO>>> getAllComments() {
        return ResponseEntity.status(200).body(
                ApiResponse.<List<CommentResponseDTO>>builder()
                        .status("success")
                        .status_code(200)
                        .message("Comments fetched")
                        .data(service.getAllComments())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CommentResponseDTO>> getCommentById(@PathVariable UUID id) {
        return ResponseEntity.status(200).body(
                ApiResponse.<CommentResponseDTO>builder()
                        .status("success")
                        .status_code(200)
                        .message("Comment found")
                        .data(service.getCommentById(id))
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CommentResponseDTO>> updateComment(
            @PathVariable UUID id,
            @RequestBody CommentRequestDTO dto
    ) {
        return ResponseEntity.status(200).body(
                ApiResponse.<CommentResponseDTO>builder()
                        .status("success")
                        .status_code(200)
                        .message("Comment updated successfully")
                        .data(service.updateComment(id, dto))
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteComment(@PathVariable UUID id) {

        service.deleteComment(id);

        return ResponseEntity.status(200).body(
                ApiResponse.<Void>builder()
                        .status("success")
                        .status_code(200)
                        .message("Comment deleted successfully")
                        .data(null)
                        .build()
        );
    }
}

