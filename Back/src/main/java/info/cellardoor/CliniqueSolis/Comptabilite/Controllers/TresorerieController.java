package info.cellardoor.CliniqueSolis.Comptabilite.Controllers;


import info.cellardoor.CliniqueSolis.Comptabilite.Http.Response.RevenuResponse.RevenuResponse;
import info.cellardoor.CliniqueSolis.Comptabilite.Http.Response.TresorerieResponse.TresorieResponse;
import info.cellardoor.CliniqueSolis.Comptabilite.Services.RevenusService;
import info.cellardoor.CliniqueSolis.Comptabilite.Services.TresorerieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tresorerie")
@RequiredArgsConstructor
public class TresorerieController {
    public final TresorerieService tresorerieService;
    public ResponseEntity<TresorieResponse> getTresorerie(){

        return ResponseEntity.ok(tresorerieService.getTresorerie());
    }



}
