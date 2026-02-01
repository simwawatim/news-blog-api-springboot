package blog.blog.Repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import blog.blog.Entities.Share;

public interface ShareRepository extends JpaRepository<Share, UUID> {}
