package blog.blog.Service.impl;

import org.springframework.stereotype.Service;

import blog.blog.DTOs.LikeRequestDTO;
import blog.blog.DTOs.LikeResponseDTO;
import blog.blog.Entities.Post;
import blog.blog.Entities.PostLike;
import blog.blog.Entities.User;
import blog.blog.Repositories.PostLikeRepository;
import blog.blog.Repositories.PostRepository;
import blog.blog.Repositories.UserRepository;
import blog.blog.Service.LikeService;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class LikeServiceImpl implements LikeService {

    private final PostLikeRepository likeRepo;
    private final PostRepository postRepo;
    private final UserRepository userRepo;

    public LikeServiceImpl(PostLikeRepository likeRepo, PostRepository postRepo, UserRepository userRepo) {
        this.likeRepo = likeRepo;
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }

    @Override
    public LikeResponseDTO likePost(LikeRequestDTO dto) {

        Post post = postRepo.findById(dto.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        likeRepo.findByPostIdAndUserId(post.getId(), user.getId())
                .ifPresent(l -> {
                    throw new RuntimeException("You already liked this post");
                });

        PostLike like = new PostLike();
        like.setPost(post);
        like.setUser(user);
        like.setLikedAt(LocalDateTime.now());

        PostLike saved = likeRepo.save(like);

        return LikeResponseDTO.builder()
                .id(saved.getId())
                .postId(saved.getPost().getId())
                .userId(saved.getUser().getId())
                .likedAt(saved.getLikedAt())
                .build();
    }

    @Override
    public String unlikePost(UUID postId, String userId) {

        PostLike like = likeRepo.findByPostIdAndUserId(postId, userId)
                .orElseThrow(() -> new RuntimeException("Like not found"));

        likeRepo.delete(like);
        return "Post unliked successfully";
    }

    @Override
    public long countLikes(UUID postId) {
        return likeRepo.countByPostId(postId);
    }
}
