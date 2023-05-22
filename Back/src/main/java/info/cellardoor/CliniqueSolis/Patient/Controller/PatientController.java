package info.cellardoor.CliniqueSolis.Patient.Controller;

import info.cellardoor.CliniqueSolis.Patient.Http.Request.PatientRequest;
import info.cellardoor.CliniqueSolis.Patient.Http.Response.ListPatientResponse;
import info.cellardoor.CliniqueSolis.Patient.Http.Response.PatientResponse;
import info.cellardoor.CliniqueSolis.Patient.Service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> getPatientById(
            @PathVariable("id") Integer id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<ListPatientResponse> getAll() {
        return ResponseEntity.ok(patientService.getAll());
    }

    @GetMapping("/search")
    public ResponseEntity<ListPatientResponse> getByCinStartingWith(
            @RequestParam(value = "cin", required = false) String cin) {
        if (cin == null) {
            return ResponseEntity.ok(patientService.getAll());
        }
        return ResponseEntity.ok(patientService.findByCinStartingWith(cin));
    }

    @PostMapping("/create")
    public ResponseEntity<PatientResponse> createPatient(
            @RequestBody PatientRequest patientRequest
    ) {
        return ResponseEntity.ok(patientService.createPatient(patientRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePatientById(

            @PathVariable("id") Integer id
    ) {
        patientService.deletePatientById(id);
        return ResponseEntity.ok("Patient deleted");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PatientResponse> updatePatientById(
            @PathVariable("id") Integer id,
            @RequestBody PatientRequest patientRequest
    ) {
        return ResponseEntity.ok(patientService.updatePatientById(id, patientRequest));
    }



}