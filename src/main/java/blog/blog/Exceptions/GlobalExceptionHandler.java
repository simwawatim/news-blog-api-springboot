package blog.blog.Exceptions;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import blog.blog.Response.ApiResponse;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Object>> handleEmailExists(EmailAlreadyExistsException ex) {
        return ResponseEntity.status(400).body(
                ApiResponse.builder()
                        .status("fail")
                        .status_code(400)
                        .message(ex.getMessage())
                        .data(null)
                        .build()
        );
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidation(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.status(400).body(
                ApiResponse.builder()
                        .status("fail")
                        .status_code(400)
                        .message("Validation error")
                        .data(errors)
                        .build()
        );
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(404).body(
                ApiResponse.builder()
                        .status("fail")
                        .status_code(404)
                        .message(ex.getMessage())
                        .data(null)
                        .build()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleAll(Exception ex) {
        return ResponseEntity.status(500).body(
                ApiResponse.builder()
                        .status("error")
                        .status_code(500)
                        .message(ex.getMessage()) 
                        .data(null)
                        .build()
        );
    }
}
