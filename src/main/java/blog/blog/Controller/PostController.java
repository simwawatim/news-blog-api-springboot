package blog.blog.Controller;
import blog.blog.DTOs.PostRequestDTO;
import blog.blog.DTOs.PostResponseDTO;
import blog.blog.Response.ApiResponse;
import blog.blog.Service.PostService;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts/")
public class PostController {

    private final PostService service;

    private PostController(PostService service){
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<PostResponseDTO>> createPost(
        @RequestBody PostRequestDTO dto
    )
    {
        return ResponseEntity.status(200).body(
            ApiResponse.<PostResponseDTO>builder()
            .status("success")
            .status_code(200)
            .message("Post created")
            .data(service.createPost(dto))
            .build()
        );
    }
    

    @GetMapping()
    public ResponseEntity<ApiResponse<List<PostResponseDTO>>> getAllPosts(){

        return ResponseEntity.status(200).body(
            ApiResponse.<List<PostResponseDTO>>builder()
            .status("success")
            .status_code(200)
            .message("Post fetched")
            .data(service.getAllPosts())
            .build()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PostResponseDTO>> getPostById(@PathVariable UUID id) { 
        return ResponseEntity.status(200).body(
            ApiResponse.<PostResponseDTO>builder() 
                .status("success")
                .status_code(200)
                .message("Post found")
                .data(service.getPostById(id)) 
                .build() 
        );
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PostResponseDTO>> updatePost(
        @PathVariable UUID id, 
        @RequestBody PostRequestDTO requestDTO
    ) {
        return ResponseEntity.status(200).body(
            ApiResponse.<PostResponseDTO>builder()
                .status("success")
                .status_code(200)
                .message("Post updated successfully")
                .data(service.updatePost(id, requestDTO))
                .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePost(@PathVariable UUID id) {
        service.deletePost(id);
        return ResponseEntity.status(200).body(
            ApiResponse.<Void>builder()
                .status("success")
                .status_code(200)
                .message("Post deleted successfully")
                .data(null) 
                .build()
        );
    }
    
}
