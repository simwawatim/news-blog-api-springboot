package blog.blog.Repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import blog.blog.Entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, UUID> {}