package blog.blog.Response;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {
    private String status;      
    private int status_code;     
    private String message;      
    private T data;             
}
