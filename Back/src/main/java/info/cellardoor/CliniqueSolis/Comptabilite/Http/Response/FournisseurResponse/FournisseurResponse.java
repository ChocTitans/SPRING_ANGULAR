package info.cellardoor.CliniqueSolis.Comptabilite.Http.Response.FournisseurResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class FournisseurResponse {
    @JsonProperty("fournisseur_id")
    private Integer fournisseurId;
    @JsonProperty("nom")
    private String nom;
    @JsonProperty("prenom")
    private String prenom;
    @JsonProperty("nom_societe")
    private String nom_societe;
    @JsonProperty("email")
    private String email;
}
