package info.cellardoor.CliniqueSolis.Comptabilite.Controllers;

import info.cellardoor.CliniqueSolis.Comptabilite.Http.Request.FournisseurRequest.FournisseurRequest;
import info.cellardoor.CliniqueSolis.Comptabilite.Http.Response.FournisseurResponse.FournisseurResponse;
import info.cellardoor.CliniqueSolis.Comptabilite.Http.Response.FournisseurResponse.ListFournisseurResponse;
import info.cellardoor.CliniqueSolis.Comptabilite.Http.Response.RevenuResponse.RevenuResponse;
import info.cellardoor.CliniqueSolis.Comptabilite.Models.repositories.FournisseurRepository;
import info.cellardoor.CliniqueSolis.Comptabilite.Services.FournisseurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fournisseur")
@RequiredArgsConstructor
public class FournisseurController {
    private final FournisseurService fournisseurService;
    @GetMapping("/{id}")
    public ResponseEntity<FournisseurResponse> getFournisseurById(
            @PathVariable("id") Integer id)
    {
        return ResponseEntity.ok(fournisseurService.getFournisseurById(id));
    }
    @PostMapping("/create")
    public ResponseEntity<FournisseurResponse> createFournisseur(
            @RequestBody FournisseurRequest fournisseurRequest
    ) {
        return ResponseEntity.ok(fournisseurService.createFournisseur(fournisseurRequest));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFournisseurById(

            @PathVariable("id") Integer id
    ) {
        fournisseurService.deleteFournisseurById(id);
        return ResponseEntity.ok("fournisseur id:" + id + " supprimé");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<FournisseurResponse> updateFournisseurById(
            @PathVariable("id") Integer id,
            @RequestBody FournisseurRequest fournisseurRequest
    ) {
        return ResponseEntity.ok(fournisseurService.updateFournisseurById(id,fournisseurRequest));
    }
    public ResponseEntity<ListFournisseurResponse> getAllFacture() {
        return ResponseEntity.ok(fournisseurService.getAll());
    }

}
