package info.cellardoor.CliniqueSolis.Comptabilite.Http.Response.FournisseurResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ListFournisseurResponse {
    private List<FournisseurResponse> fournisseurs;
}
