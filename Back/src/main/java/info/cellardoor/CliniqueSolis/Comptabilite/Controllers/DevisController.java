package info.cellardoor.CliniqueSolis.Comptabilite.Controllers;
import info.cellardoor.CliniqueSolis.Comptabilite.Http.Request.DevisRequest.DevisRequest;
import info.cellardoor.CliniqueSolis.Comptabilite.Http.Response.DevisResponse.DevisResponse;
import info.cellardoor.CliniqueSolis.Comptabilite.Services.DevisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/devis")
@RequiredArgsConstructor
public class DevisController {
    private final DevisService devisService;
    @GetMapping("/{id}")
    public ResponseEntity<DevisResponse> getDevisById(
            @PathVariable("id") Integer id)
    {
        return ResponseEntity.ok(devisService.getDevisById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<DevisResponse> createDevis(
            @RequestBody DevisRequest devisRequest
    ) {
        return ResponseEntity.ok(devisService.createDevis(devisRequest));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFactureById(

            @PathVariable("id") Integer id
    ) {
        devisService.deleteFactureById(id);
        return ResponseEntity.ok("facture id:" + id + " supprimé");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<DevisResponse> updateFactureById(
            @PathVariable("id") Integer id,
            @RequestBody DevisRequest devisRequest
    ) {
        return ResponseEntity.ok(devisService.updateDevisById(id, devisRequest));
    }


}
