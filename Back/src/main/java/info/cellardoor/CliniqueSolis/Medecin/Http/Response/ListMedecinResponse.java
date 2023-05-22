package info.cellardoor.CliniqueSolis.Medecin.Http.Response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListMedecinResponse {
    private List<MedecinResponse> medecins;
}
