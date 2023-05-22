package info.cellardoor.CliniqueSolis.RendezVous.Http.Reponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RendezVousResponse {
    @JsonProperty("rendez_vous_id")
    private Integer rendezVousId;
    @JsonProperty("patient_id")
    private Integer patientId;
    @JsonProperty("medecin_id")
    private Integer medecinId;
    @JsonProperty("date")
    private String date;
    @JsonProperty("heure")
    private String heure;
    @JsonProperty("duree")
    private Integer duree;

}