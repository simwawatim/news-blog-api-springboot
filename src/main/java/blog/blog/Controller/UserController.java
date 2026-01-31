package blog.blog.Controller;

import java.util.List;
import blog.blog.DTOs.UserRequestDTO;
import blog.blog.DTOs.UserResponseDTO;
import blog.blog.Response.ApiResponse;
import blog.blog.Service.UserService;
import lombok.RequiredArgsConstructor;

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
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponseDTO>>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();

        return ResponseEntity.ok(
                ApiResponse.<List<UserResponseDTO>>builder()
                        .status("success")
                        .status_code(200)
                        .message("Users fetched successfully")
                        .data(users)
                        .build()
        );
    }


    @PostMapping
    public ResponseEntity<ApiResponse<UserResponseDTO>> createUser(@RequestBody UserRequestDTO request) {
        UserResponseDTO user = userService.createUser(request);

        return ResponseEntity.status(201).body(
                ApiResponse.<UserResponseDTO>builder()
                        .status("success")
                        .status_code(201)
                        .message("User created successfully")
                        .data(user)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getUserById(@PathVariable String id){
        UserResponseDTO  user = userService.getUserById(id);
        return ResponseEntity.ok(
            ApiResponse.<UserResponseDTO>builder()
            .status("sucess")
            .status_code(200)
            .data(user)
            .message("User fetched successfully")
            .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> updateUser(
            @PathVariable String id,
            @RequestBody UserRequestDTO request
    ) {
        UserResponseDTO updatedUser = userService.updateUser(id, request);

        return ResponseEntity.ok(
                ApiResponse.<UserResponseDTO>builder()
                        .status("success")
                        .status_code(200)
                        .message("User updated successfully")
                        .data(updatedUser)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteUser (@PathVariable String id){
        userService.deleteUser(id);
        return ResponseEntity.ok(
            ApiResponse.builder()
            .status("success")
            .status_code(200)
            .message("User deleted successfully")
            .data(null)
            .build()
        );
    }

}
