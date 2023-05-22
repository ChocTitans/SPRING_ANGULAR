package info.cellardoor.CliniqueSolis.Comptabilite.Controllers;

import info.cellardoor.CliniqueSolis.Comptabilite.Http.Response.DepenseResponse.DepenseResponse;
import info.cellardoor.CliniqueSolis.Comptabilite.Http.Response.FactureResponse.FactureResponse;
import info.cellardoor.CliniqueSolis.Comptabilite.Services.DepenseService;
import info.cellardoor.CliniqueSolis.Patient.Http.Request.PatientRequest;
import info.cellardoor.CliniqueSolis.Patient.Http.Response.ListPatientResponse;
import info.cellardoor.CliniqueSolis.Patient.Http.Response.PatientResponse;
import info.cellardoor.CliniqueSolis.Patient.Service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/depense")
@RequiredArgsConstructor

public class DepenseController {

    private final DepenseService depenseService;
    @GetMapping("/all")
    public  ResponseEntity<DepenseResponse> getAll(){
        return ResponseEntity.ok(depenseService.getAll());
}
}
