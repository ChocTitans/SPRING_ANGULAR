package info.cellardoor.CliniqueSolis.Medecin.Http.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedecinRequest {
    private String nom;
    private String prenom;
    private String cin;
    private String email;
    private String mdp;
    private String specialite;
    private String diplome;
    private String role;

}