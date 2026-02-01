package blog.blog.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import blog.blog.Entities.PostLike;

import java.util.Optional;
import java.util.UUID;

public interface PostLikeRepository extends JpaRepository<PostLike, UUID> {

    Optional<PostLike> findByPostIdAndUserId(UUID postId, String userId);

    long countByPostId(UUID postId);
}
