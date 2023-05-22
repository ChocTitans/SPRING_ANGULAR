package info.cellardoor.CliniqueSolis.Auth.Service;

import info.cellardoor.CliniqueSolis.App.Helpers;
import info.cellardoor.CliniqueSolis.Auth.Http.Request.UserRequest;
import info.cellardoor.CliniqueSolis.Auth.Http.Response.ListUserResponse;
import info.cellardoor.CliniqueSolis.Auth.Http.Response.UserResponse;
import info.cellardoor.CliniqueSolis.Auth.Models.Token.TokenRepository;
import info.cellardoor.CliniqueSolis.Auth.Models.User.Roles;
import info.cellardoor.CliniqueSolis.Auth.Models.User.User;
import info.cellardoor.CliniqueSolis.Auth.Models.User.UserDTO;
import info.cellardoor.CliniqueSolis.Auth.Models.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;

    public ListUserResponse getAll() {
        List<User> users = userRepository.findAll();
        users.removeIf(user -> !(user.getRole().name().equals(Roles.ROLE_ADMIN.name())
                || user.getRole().name().equals(Roles.ROLE_UTILISATEUR.name()))
        );
        return ListUserResponse.builder()
                .users(users.stream().map(UserDTO::build).toList()).build();

    }

    public UserResponse getById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
        return UserResponse.builder()
                .id(user.getUserId())
                .nom(user.getNom())
                .prenom(user.getPrenom())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }

    public void deleteById(Integer id) {
        var user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
        tokenRepository.deleteAllByUser(user);
        userRepository.deleteById(id);
    }

    public UserResponse updateById(Integer id, UserRequest userRequest) {
        var user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
        return getUserResponse(userRequest, user);
    }

    private UserResponse getUserResponse(UserRequest userRequest, User user) {
        BeanUtils.copyProperties(userRequest, user, Helpers.getNullPropertyNames(userRequest));
        var savedUser = userRepository.save(user);
        return UserDTO.build(savedUser);
    }

    public ListUserResponse search(String query) {
        List<User> users = userRepository.findAll();
        users.removeIf(user -> !(user.getRole().name().equals(Roles.ROLE_ADMIN.name())
                || user.getRole().name().equals(Roles.ROLE_UTILISATEUR.name()))
        );
        users.removeIf(user -> !user.getNom().toLowerCase().contains(query.toLowerCase())
                && !user.getPrenom().toLowerCase().contains(query.toLowerCase())
                && !user.getEmail().toLowerCase().contains(query.toLowerCase())
        );
        return ListUserResponse.builder()
                .users(users.stream().map(UserDTO::build).toList()).build();
    }

    public UserResponse create(UserRequest request) {
        var user = User.builder()
                .prenom(request.getPrenom())
                .nom(request.getNom())
                .email(request.getEmail())
                .mdp(passwordEncoder.encode(request.getPassword()))
                .role(Roles.ROLE_UTILISATEUR)
                .build();
        var savedUser = userRepository.save(user);
        return UserResponse.builder()
                .nom(savedUser.getNom())
                .prenom(savedUser.getPrenom())
                .email(savedUser.getEmail())
                .role(user.getRole().name())
                .build();
    }
}