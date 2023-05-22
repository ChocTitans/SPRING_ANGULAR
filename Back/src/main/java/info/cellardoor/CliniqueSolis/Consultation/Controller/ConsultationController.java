package info.cellardoor.CliniqueSolis.Consultation.Controller;

import info.cellardoor.CliniqueSolis.Consultation.Http.Request.ConsultationRequest;
import info.cellardoor.CliniqueSolis.Consultation.Http.Response.ConsultationResponse;
import info.cellardoor.CliniqueSolis.Consultation.Service.ConsultationService;
import info.cellardoor.CliniqueSolis.RendezVous.Http.Reponse.ListRendezVousResponse;
import info.cellardoor.CliniqueSolis.RendezVous.Http.Reponse.RendezVousResponse;
import info.cellardoor.CliniqueSolis.RendezVous.Http.Request.RendezVousRequest;
import info.cellardoor.CliniqueSolis.RendezVous.Service.RendezVousService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consultation")
@RequiredArgsConstructor
public class ConsultationController {
    private final ConsultationService consultationService;

    @PostMapping("/createConsultation")
    public ResponseEntity<ConsultationResponse> createConsultation(
            @RequestBody ConsultationRequest ConsultationRequest
    ) {
        return ResponseEntity.ok(consultationService.createConsultation(ConsultationRequest));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ConsultationResponse> getConsultationById(
            @PathVariable("id") Integer id)
    {
        return ResponseEntity.ok(consultationService.getConsultationById(id));
    }
    @PutMapping("/updateConsultation/{id}")
    public ResponseEntity<ConsultationResponse> updateConsltationById(
            @PathVariable("id") Integer id,
            @RequestBody ConsultationRequest consultationRequest
    ) {
        return ResponseEntity.ok(consultationService.updateConsultationById(id, consultationRequest));
    }
    @DeleteMapping("/deleteConsultation/{id}")
    public ResponseEntity<String> deleteConsultationById(

            @PathVariable("id") Integer id
    ) {
        consultationService.deleteConsultationById(id);
        return ResponseEntity.ok("Consultation id:" + id + " supprimé");
    }

}
