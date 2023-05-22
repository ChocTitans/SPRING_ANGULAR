package info.cellardoor.CliniqueSolis.Consultation.Http.Request;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ConsultationRequest {
    private Integer consultationId;
    private Integer rendezVousId;
    private Integer medecinId;
    private Integer patientId;
    private String observations;
    private String dateConsultation;
    private List<PrescriptionRequest> prescriptions;

}
