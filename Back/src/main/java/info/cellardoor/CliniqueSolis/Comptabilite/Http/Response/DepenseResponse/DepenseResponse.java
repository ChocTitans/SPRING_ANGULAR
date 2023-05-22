package info.cellardoor.CliniqueSolis.Comptabilite.Http.Response.DepenseResponse;

import info.cellardoor.CliniqueSolis.Comptabilite.Http.Response.DevisResponse.DevisResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class DepenseResponse {
    private List<DevisResponse> devis;
}
