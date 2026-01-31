
package blog.blog.Repositories;
import java.util.UUID;
import blog.blog.Entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, UUID>{
    
}
