package info.cellardoor.CliniqueSolis.Comptabilite.Http.Request.FournisseurRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FournisseurRequest {
    private String nom;
    private String prenom;
    private String nom_societe;
    private String email;
}
