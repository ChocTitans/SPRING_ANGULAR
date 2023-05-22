package info.cellardoor.CliniqueSolis.Patient.Service;

import info.cellardoor.CliniqueSolis.Patient.Http.Response.PatientResponse;
import info.cellardoor.CliniqueSolis.Patient.Models.Patient;

public class PatientDTO {
    public static PatientResponse build (Patient patient){
        return PatientResponse.builder()
                .patientId(patient.getPatientId())
                .nom(patient.getUser().getNom())
                .prenom(patient.getUser().getPrenom())
                .cin(patient.getCin())
                .email(patient.getUser().getEmail())
                .antecedents(patient.getAntecedents())
                .build();
    }
}
