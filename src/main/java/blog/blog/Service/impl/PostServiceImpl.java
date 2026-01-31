package blog.blog.Service.impl;
import blog.blog.DTOs.PostRequestDTO;
import blog.blog.DTOs.PostResponseDTO;
import blog.blog.Entities.Post;
import blog.blog.Entities.Tag;
import blog.blog.Entities.User;
import blog.blog.Repositories.PostRepository;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import blog.blog.Service.PostService;
import blog.blog.Repositories.UserRepository;
import blog.blog.Repositories.TagRepository;

@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepo;
    private final UserRepository userRepo;
    private final TagRepository tagRepo;


    public PostServiceImpl(PostRepository postRepo,  UserRepository userRepo, TagRepository tagRepo){
        this.postRepo = postRepo;
        this.userRepo = userRepo;
        this.tagRepo = tagRepo;
    }


     @Override
    public PostResponseDTO createPost(PostRequestDTO dto) {

        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = new Post();
        post.setUser(user);
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setCreatedAt(LocalDateTime.now());

        int words = dto.getContent().split("\\s+").length;
        post.setReadTime(Math.max(1, words / 200));
        if (dto.getTags() != null && !dto.getTags().isEmpty()) {
            Set<Tag> tags = dto.getTags().stream()
                    .map(name -> tagRepo.findAll().stream()
                            .filter(t -> t.getName().equalsIgnoreCase(name))
                            .findFirst()
                            .orElseGet(() -> tagRepo.save(Tag.builder().name(name).build()))
                    )
                    .collect(Collectors.toSet());

            post.setTags(tags);
        }

        Post saved = postRepo.save(post);
        return mapToResponse(saved);
    }

    @Override
    public List<PostResponseDTO> getAllPosts() {
        return postRepo.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private PostResponseDTO mapToResponse(Post post) {

        Set<String> tagNames = post.getTags() == null ? new HashSet<>() :
                post.getTags().stream()
                        .map(Tag::getName)
                        .collect(Collectors.toSet());

        return PostResponseDTO.builder()
                .id(post.getId())
                .userId(post.getUser().getId())
                .fullName(post.getUser().getFullName())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .readTime(post.getReadTime())
                .tags(tagNames)
                .likeCount(post.getLikes() == null ? 0 : post.getLikes().size())
                .commentCount(post.getComments() == null ? 0 : post.getComments().size())
                .shareCount(post.getShares() == null ? 0 : post.getShares().size())
                .build();
    }


    @Override
    public PostResponseDTO getPostById(UUID id) {
        Post post = postRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return mapToResponse(post);
    }

    @Override
    public PostResponseDTO updatePost(UUID id, PostRequestDTO dto) {

        Post post = postRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        int words = dto.getContent().split("\\s+").length;
        post.setReadTime(Math.max(1, words / 200));

        Post updated = postRepo.save(post);
        return mapToResponse(updated);
    }

    @Override
    public void deletePost(UUID id) {
        Post post = postRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        postRepo.delete(post);
    }

}
