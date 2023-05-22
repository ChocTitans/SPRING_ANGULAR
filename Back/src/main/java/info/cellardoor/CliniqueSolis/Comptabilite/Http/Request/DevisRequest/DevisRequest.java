package info.cellardoor.CliniqueSolis.Comptabilite.Http.Request.DevisRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.cellardoor.CliniqueSolis.Comptabilite.Models.Status;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DevisRequest {

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
