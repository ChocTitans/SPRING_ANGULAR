package info.cellardoor.CliniqueSolis.Medecin.Controller;

import info.cellardoor.CliniqueSolis.Medecin.Http.Request.MedecinRequest;
import info.cellardoor.CliniqueSolis.Medecin.Http.Response.ListMedecinResponse;
import info.cellardoor.CliniqueSolis.Medecin.Http.Response.MedecinResponse;
import info.cellardoor.CliniqueSolis.Medecin.Service.MedecinService;
import info.cellardoor.CliniqueSolis.Patient.Http.Response.ListPatientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medecin")
@RequiredArgsConstructor
public class MedecinController {
    private final MedecinService medecinService;
    @GetMapping("/{id}")
    public ResponseEntity<MedecinResponse> getMedecinById(
            @PathVariable("id") Integer id)
    {
        return ResponseEntity.ok(medecinService.getMedecinById(id));
    }
    @PostMapping("/create")
    public ResponseEntity<MedecinResponse> createMedecin(
            @RequestBody MedecinRequest medecinRequest
    ) {
        return ResponseEntity.ok(medecinService.createMedecin(medecinRequest));
    }
    @GetMapping("/all")
    public ResponseEntity<ListMedecinResponse> getAll() {
        return ResponseEntity.ok(medecinService.getAll());
    }
    @GetMapping("/search")
    public ResponseEntity<ListMedecinResponse> getByCinStartingWith(
            @RequestParam(value = "cin", required = false) String cin) {
        if (cin == null) {
            return ResponseEntity.ok(medecinService.getAll());
        }
        return ResponseEntity.ok(medecinService.findByCinStartingWith(cin));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMedecinById(

            @PathVariable("id") Integer id
    ) {
        medecinService.deleteMedecinById(id);
        return ResponseEntity.ok("Medecin id:" + id + " supprimé");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<MedecinResponse> updateMedecinById(
            @PathVariable("id") Integer id,
            @RequestBody MedecinRequest medecinRequest
    ) {
        return ResponseEntity.ok(medecinService.updateMedecinById(id, medecinRequest));
    }

}