package info.cellardoor.CliniqueSolis.Consultation.Http.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListConsultationResponse {
    private List<ConsultationResponse> consultations;
}

