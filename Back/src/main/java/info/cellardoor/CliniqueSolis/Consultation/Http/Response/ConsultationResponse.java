package info.cellardoor.CliniqueSolis.Consultation.Http.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationResponse {
    @JsonProperty("consultation_id")
    private Integer consultationId;
    @JsonProperty("rendez_vous_id")
    private Integer rendezVousId;
    @JsonProperty("observations")
    private String observations;
    @JsonProperty("date_consultation")
    private String dateConsultation;
    @JsonProperty("prescriptions")
    private List<PrescriptionResponse> prescriptions;


}
