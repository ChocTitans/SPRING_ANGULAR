package info.cellardoor.CliniqueSolis.Auth.Models.User;


import info.cellardoor.CliniqueSolis.Auth.Http.Response.UserResponse;

public class UserDTO {
    public static UserResponse build (User user){
        return UserResponse.builder()
                .id(user.getUserId())
                .nom(user.getNom())
                .prenom(user.getPrenom())
                .email(user.getEmail())
                .role(user.getRole().name())
                .isActive(user.getStatus())
                .build();
    }
}