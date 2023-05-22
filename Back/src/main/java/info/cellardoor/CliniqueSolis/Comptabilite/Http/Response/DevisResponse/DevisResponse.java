package info.cellardoor.CliniqueSolis.Comptabilite.Http.Response.DevisResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.cellardoor.CliniqueSolis.Comptabilite.Models.Fournisseur;
import info.cellardoor.CliniqueSolis.Comptabilite.Models.Status;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DevisResponse {
    @JsonProperty("devis_id")
    private Integer devisId;
    @JsonProperty("fournisseur_name")
    private String fournisseur_name;
    @JsonProperty("montant")
    private String montant;
    @JsonProperty("description")
    private String description ;

    @JsonProperty("type_achat")
    private String type_achat;
    @JsonProperty("status")
    private Status status ;
}
