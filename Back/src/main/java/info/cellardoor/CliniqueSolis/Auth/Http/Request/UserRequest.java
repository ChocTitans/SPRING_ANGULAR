package info.cellardoor.CliniqueSolis.Auth.Http.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String nom;
    private String prenom;
    private String email;
    private String role;
    private String password;
}