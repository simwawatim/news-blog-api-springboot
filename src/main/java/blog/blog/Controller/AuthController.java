package blog.blog.Controller;

import blog.blog.DTOs.LoginRequestDTO;
import blog.blog.DTOs.LoginResponseDTO;
import blog.blog.Response.ApiResponse;
import blog.blog.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDTO>> login(@RequestBody LoginRequestDTO request) {
        LoginResponseDTO loginResponse = authService.login(request);

        return ResponseEntity.status(200).body(
                ApiResponse.<LoginResponseDTO>builder()
                        .status("success")
                        .status_code(200)
                        .message("Login successful")
                        .data(loginResponse)
                        .build()
        );
    }
}
