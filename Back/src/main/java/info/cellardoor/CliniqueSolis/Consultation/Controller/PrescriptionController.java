package info.cellardoor.CliniqueSolis.Consultation.Controller;

import info.cellardoor.CliniqueSolis.Consultation.Http.Request.ConsultationRequest;
import info.cellardoor.CliniqueSolis.Consultation.Http.Request.PrescriptionRequest;
import info.cellardoor.CliniqueSolis.Consultation.Http.Response.ConsultationResponse;
import info.cellardoor.CliniqueSolis.Consultation.Http.Response.PrescriptionResponse;
import info.cellardoor.CliniqueSolis.Consultation.Service.ConsultationService;
import info.cellardoor.CliniqueSolis.Consultation.Service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prescription")
@RequiredArgsConstructor
public class PrescriptionController {
    private final PrescriptionService prescriptionService;

    @PostMapping("/createPrescription")
    public ResponseEntity<PrescriptionResponse> createPrescription(
            @RequestBody PrescriptionRequest prescriptionRequest
    ) {
        return ResponseEntity.ok(prescriptionService.createPrescription(prescriptionRequest));
    }
    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionResponse> getPrescriptionById(
            @PathVariable("id") Integer id)
    {
        return ResponseEntity.ok(prescriptionService.getPrescriptionById(id));
    }
    @PutMapping("/updatePrescription/{id}")
    public ResponseEntity<PrescriptionResponse> updatePrescriptionById(
            @PathVariable("id") Integer id,
            @RequestBody PrescriptionRequest prescriptionRequest
    ) {
        return ResponseEntity.ok(prescriptionService.updatePrescriptionById(id, prescriptionRequest));
    }
    @DeleteMapping("/deletePrescription/{id}")
    public ResponseEntity<String> deletePrescriptionById(

            @PathVariable("id") Integer id
    ) {
        prescriptionService.deletePrescriptionById(id);
        return ResponseEntity.ok("Prescription id:" + id + " supprimé");
    }

}
