package info.cellardoor.CliniqueSolis.Consultation.Http.Request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionRequest {
    private Integer prescriptionId;
    private Integer consultationId;
    private String medicament;
    private Integer duree;

}
