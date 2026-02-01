package blog.blog.Repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import blog.blog.Entities.CommentReply;

public interface CommentReplyRepository extends JpaRepository<CommentReply, UUID> {}