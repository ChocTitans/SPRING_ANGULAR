package info.cellardoor.CliniqueSolis.Comptabilite.Http.Response.FactureResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FactureResponse {

    @JsonProperty("facture_id")
    private Integer factureId;
    @JsonProperty("cin")
    private String cin;
    @JsonProperty("montant")
    private Long montant ;
    @JsonProperty("type_service")
    private String type_service;


}
