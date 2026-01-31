package blog.blog.Repositories;
import blog.blog.Entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
public interface TagRepository extends JpaRepository<Tag, UUID> {

    
} 