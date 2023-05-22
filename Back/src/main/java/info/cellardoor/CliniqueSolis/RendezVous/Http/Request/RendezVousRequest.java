package info.cellardoor.CliniqueSolis.RendezVous.Http.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RendezVousRequest {
    private String date;
    private String heure;
    private Integer duree;
    private Integer medecinId;
    private Integer patientId;
}