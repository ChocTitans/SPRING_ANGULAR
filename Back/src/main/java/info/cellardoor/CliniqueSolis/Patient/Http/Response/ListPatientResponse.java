package info.cellardoor.CliniqueSolis.Patient.Http.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListPatientResponse {
    private List<PatientResponse> patients;

}
