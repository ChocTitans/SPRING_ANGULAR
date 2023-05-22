package info.cellardoor.CliniqueSolis.Medecin.Http.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedecinResponse {
    @JsonProperty("medecin_id")
    private Integer medecinId;
    @JsonProperty("cin")
    private String cin;
    @JsonProperty("specialite")
    private String specialite;
    @JsonProperty("diplome")
    private String diplome;
    @JsonProperty("nom")
    private String nom;
    @JsonProperty("prenom")
    private String prenom;
    @JsonProperty("email")
    private String email;
    @JsonProperty("role")
    private String role;
}