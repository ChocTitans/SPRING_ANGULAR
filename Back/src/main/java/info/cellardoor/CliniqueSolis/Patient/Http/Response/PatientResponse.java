package info.cellardoor.CliniqueSolis.Patient.Http.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.cellardoor.CliniqueSolis.Patient.Models.Antecedent;
import info.cellardoor.CliniqueSolis.Patient.Models.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientResponse {
    @JsonProperty("patient_id")
    private Integer patientId;
    @JsonProperty("nom")
    private String nom;
    @JsonProperty("prenom")
    private String prenom;
    @JsonProperty("email")
    private String email;
    @JsonProperty("cin")
    private String cin;
    private Antecedent antecedents;
}