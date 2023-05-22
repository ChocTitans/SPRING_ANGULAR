package info.cellardoor.CliniqueSolis.Comptabilite.Http.Response.RevenuResponse;

import info.cellardoor.CliniqueSolis.Comptabilite.Http.Response.FactureResponse.FactureResponse;
import info.cellardoor.CliniqueSolis.Comptabilite.Models.Facture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
@AllArgsConstructor
public class RevenuResponse {
    private List<FactureResponse> factures;

}
