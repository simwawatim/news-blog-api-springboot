package blog.blog.Controller;
import blog.blog.DTOs.LikeRequestDTO;
import blog.blog.DTOs.LikeResponseDTO;
import blog.blog.Response.ApiResponse;
import blog.blog.Service.LikeService;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/likes/")
public class LikeController {

    private final LikeService service;

    private LikeController(LikeService service){
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<LikeResponseDTO>> likePost(
        @RequestBody LikeRequestDTO dto
    ){
        return ResponseEntity.status(200).body(
            ApiResponse.<LikeResponseDTO>builder()
                .status("success")
                .status_code(200)
                .message("Post liked successfully")
                .data(service.likePost(dto))
                .build()
        );
    }

    @DeleteMapping()
    public ResponseEntity<ApiResponse<Void>> unlikePost(
        @RequestParam UUID postId,
        @RequestParam String userId
    ){
        service.unlikePost(postId, userId);

        return ResponseEntity.status(200).body(
            ApiResponse.<Void>builder()
                .status("success")
                .status_code(200)
                .message("Post unliked successfully")
                .data(null)
                .build()
        );
    }

    @GetMapping("/count/{postId}")
    public ResponseEntity<ApiResponse<Long>> countLikes(@PathVariable UUID postId){

        return ResponseEntity.status(200).body(
            ApiResponse.<Long>builder()
                .status("success")
                .status_code(200)
                .message("Likes count fetched")
                .data(service.countLikes(postId))
                .build()
        );
    }
}
