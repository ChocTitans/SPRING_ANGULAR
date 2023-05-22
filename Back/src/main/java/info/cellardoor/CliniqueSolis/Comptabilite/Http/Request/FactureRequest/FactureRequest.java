package info.cellardoor.CliniqueSolis.Comptabilite.Http.Request.FactureRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.JoinColumn;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FactureRequest {
    private String cin;
    private Long montant ;
    private String type_service;
}
