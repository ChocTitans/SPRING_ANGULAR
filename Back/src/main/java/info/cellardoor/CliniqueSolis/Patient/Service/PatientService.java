package info.cellardoor.CliniqueSolis.Patient.Service;

import info.cellardoor.CliniqueSolis.App.Helpers;
import info.cellardoor.CliniqueSolis.Auth.Models.User.Roles;
import info.cellardoor.CliniqueSolis.Auth.Models.User.User;
import info.cellardoor.CliniqueSolis.Auth.Models.User.UserRepository;
import info.cellardoor.CliniqueSolis.Patient.Http.Request.PatientRequest;
import info.cellardoor.CliniqueSolis.Patient.Http.Response.ListPatientResponse;
import info.cellardoor.CliniqueSolis.Patient.Http.Response.PatientResponse;
import info.cellardoor.CliniqueSolis.Patient.Models.Antecedent;
import info.cellardoor.CliniqueSolis.Patient.Models.Patient;
import info.cellardoor.CliniqueSolis.Patient.Models.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public PatientResponse getPatientById(Integer id) {
        var patient = patientRepository.findByPatientId(id).orElseThrow(() -> new NoSuchElementException("Patient not found"));
        return PatientDTO.build(patient);
    }

    public ListPatientResponse findByCinStartingWith(String cin) {
        List<Patient> patients = patientRepository.findByCinStartingWith(cin);
        if (patients.size() == 0)
            return null;
        return ListPatientResponse.builder()
                .patients(patients.stream().map(PatientDTO::build).toList()).build();
    }

    public PatientResponse createPatient(PatientRequest patientRequest) {
        var associatedUser = User.builder()
                .nom(patientRequest.getNom())
                .prenom(patientRequest.getPrenom())
                .email(patientRequest.getEmail())
                .mdp(passwordEncoder.encode(patientRequest.getMdp()))
                .role(Roles.ROLE_PATIENT)
                .build();
        var associatedantecedent = Antecedent.builder()
                .build();

        var patient = Patient.builder()
                .cin(patientRequest.getCin())
                .user(associatedUser)
                .antecedents(associatedantecedent)
                .build();
        var savedPatient = patientRepository.save(patient);
        return PatientDTO.build(savedPatient);
    }

    public void deletePatientById(Integer id) {
        var patient = patientRepository.findByPatientId(id)
                .orElseThrow(() -> new NoSuchElementException("Patient not found"));
        patientRepository.delete(patient);
    }


    public PatientResponse updatePatientById(Integer id, PatientRequest patientRequest) {
        var patient = patientRepository.findByPatientId(id)
                .orElseThrow(() -> new NoSuchElementException("Patient not found"));
        return getPatientResponse(patientRequest, patient);
    }


    public PatientResponse getPatientResponse(PatientRequest patientRequest, Patient patient) {
        BeanUtils.copyProperties(patientRequest, patient, Helpers.getNullPropertyNames(patientRequest));
        var savedPatient = patientRepository.save(patient);
        var user = patient.getUser();
        BeanUtils.copyProperties(patientRequest, user, Helpers.getNullPropertyNames(patientRequest));
        userRepository.save(user);
        return PatientDTO.build(savedPatient);
    }


    public ListPatientResponse getAll() {
        var patients = patientRepository.findAll();
        if (patients.size() == 0)
            return null;
        return ListPatientResponse.builder()
                .patients(patients.stream().map(PatientDTO::build).toList()).build();
    }
}