package blog.blog.Service;
import blog.blog.DTOs.*;
import blog.blog.Entities.Profile;
import blog.blog.Entities.User;
import blog.blog.Exceptions.EmailAlreadyExistsException;
import blog.blog.Exceptions.UserNotFoundException;
import blog.blog.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;


@Service 
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private UserResponseDTO mapToResponse(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setFullName(user.getFullName());
        dto.setEmail(user.getEmail());

        if (user.getProfile() != null) {
            dto.setWebsite(user.getProfile().getWebsite());
            dto.setAddress(user.getProfile().getAddress());
        }

        dto.setFollowersCount(user.getFollowers().size());
        dto.setFollowingCount(user.getFollowing().size());

        return dto;
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }


    public UserResponseDTO createUser(UserRequestDTO request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        Profile profile = new Profile();
        profile.setWebsite(request.getWebsite());
        profile.setAddress(request.getAddress());
        profile.setUser(user);

        user.setProfile(profile);

        User saved = userRepository.save(user);
        return mapToResponse(saved);
    }


     public UserResponseDTO getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return mapToResponse(user);
    }

    public UserResponseDTO updateUser(String id, UserRequestDTO request){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        
        if (user.getProfile() == null){
            Profile profile = new Profile();
            profile.setUser(user);
            user.setProfile(profile);
        }
        user.getProfile().setAddress(request.getAddress());
        user.getProfile().setWebsite(request.getWebsite());

        User saved = userRepository.save(user);
        return mapToResponse(saved);
    }

    public void deleteUser(String id){
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.delete(user);
    }

}
