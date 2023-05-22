package info.cellardoor.CliniqueSolis.Comptabilite.Controllers;

import info.cellardoor.CliniqueSolis.Comptabilite.Http.Response.RevenuResponse.RevenuResponse;
import info.cellardoor.CliniqueSolis.Comptabilite.Services.RevenusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/revenus")
@RequiredArgsConstructor
public class RevenusController {
    public final RevenusService revenusService;
    @GetMapping("/all")
    public ResponseEntity<RevenuResponse> getAllFacture() {
        return ResponseEntity.ok(revenusService.getAll());
    }
}
