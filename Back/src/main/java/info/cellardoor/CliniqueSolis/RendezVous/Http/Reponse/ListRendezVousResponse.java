package info.cellardoor.CliniqueSolis.RendezVous.Http.Reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListRendezVousResponse {
    private List<RendezVousResponse> rendezVous;
}