package info.cellardoor.CliniqueSolis.RendezVous.Controller;

import info.cellardoor.CliniqueSolis.Medecin.Http.Request.MedecinRequest;
import info.cellardoor.CliniqueSolis.Medecin.Http.Response.MedecinResponse;
import info.cellardoor.CliniqueSolis.Medecin.Http.Response.MedecinResponse;
import info.cellardoor.CliniqueSolis.RendezVous.Http.Reponse.ListRendezVousResponse;
import info.cellardoor.CliniqueSolis.RendezVous.Http.Reponse.RendezVousResponse;
import info.cellardoor.CliniqueSolis.RendezVous.Http.Request.RendezVousRequest;
import info.cellardoor.CliniqueSolis.RendezVous.Models.RendezVous;
import info.cellardoor.CliniqueSolis.RendezVous.Service.RendezVousService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/rendezvous")
@RequiredArgsConstructor
public class RendezVousController {

    private final RendezVousService rendezVousService;

    @GetMapping("/")
    @PreAuthorize("hasAnyRole({'ROLE_UTILISATEUR', 'ROLE_ADMIN'})")
    public ResponseEntity<ListRendezVousResponse> getAllRendezVous() {
        return ResponseEntity.ok(rendezVousService.getAll());
    }

    @GetMapping("/filtrerParDate")
    public ResponseEntity<ListRendezVousResponse> getByDate(
            @RequestParam(value = "annee", required = false) Integer annee,
            @RequestParam(value = "mois", required = false) Integer mois,
            @RequestParam(value = "jour", required = false) Integer jour
    ) {
        if (jour == null){
            if (mois == null){
                if (annee == null)
                    return ResponseEntity.ok(rendezVousService.getAll());
                return ResponseEntity.ok(rendezVousService.getByPartialDate(annee.toString()));
            }
            return ResponseEntity.ok(rendezVousService.getByPartialDate(annee + "-" + String.format("%02d", mois)));
        }
        if (annee == null || mois == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(rendezVousService.getByDate(annee + "-" + String.format("%02d", mois) + "-" + String.format("%02d", jour)));
    }

    //    @PostMapping("/createRDV")
//    public ResponseEntity<RendezVousResponse> createRendezVous(@RequestBody RendezVousRequest rendezVousRequest) {
//        RendezVous rendezVous = rendezVousService.createRendezVous(rendezVousRequest);
//        return ResponseEntity.ok(RendezVousResponse.builder()
//                .rendezVousId(rendezVous.getRendezVousId())
//                .patientId(rendezVous.getPatient().getPatientId())
//                .medecinId(rendezVous.getMedecin().getMedecinId())
//                .date(rendezVous.getDate())
//                .heure(rendezVous.getHeure())
//                .duree(rendezVous.getDuree())
//                .build());
//    }
    @PostMapping("/createRDV")
    public ResponseEntity<RendezVousResponse> createRendezVous(
            @RequestBody RendezVousRequest RendezVousRequest
    ) {
        return ResponseEntity.ok(rendezVousService.createRendezVous(RendezVousRequest));
    }
    @GetMapping("/searchRDV")
    public ResponseEntity<ListRendezVousResponse> getRDVByDate(
            @RequestParam(value = "date", required = false) String date) {
        if (date == null) {
            return ResponseEntity.ok(rendezVousService.getAll());
        }
        return ResponseEntity.ok(rendezVousService.findRendezVousByDate(date));
    }
    @GetMapping("/{id}")
    public ResponseEntity<RendezVousResponse> getRendezVousById(
            @PathVariable("id") Integer id)
    {
        return ResponseEntity.ok(rendezVousService.getRendezVousById(id));
    }
    @PutMapping("/updateRDV/{id}")
    public ResponseEntity<RendezVousResponse> updateRendezVousnById(
            @PathVariable("id") Integer id,
            @RequestBody RendezVousRequest RendezVousRequest
    ) {
        return ResponseEntity.ok(rendezVousService.updateRendezVousById(id, RendezVousRequest));
    }
    //    @PutMapping("updateRDV/{id}")
//    public ResponseEntity<RendezVousResponse> updateRendezVous(@PathVariable("id") Integer rendezVousId,
//                                                               @RequestBody RendezVousRequest rendezVousRequest) {
//        RendezVous rendezVous = rendezVousService.updateRendezVous(rendezVousId, rendezVousRequest);
//        if (rendezVous == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(RendezVousResponse.builder()
//                .rendezVousId(rendezVous.getRendezVousId())
//                .patientId(rendezVous.getPatient().getPatientId())
//                .medecinId(rendezVous.getMedecin().getMedecinId())
//                .date(rendezVous.getDate())
//                .heure(rendezVous.getHeure())
//                .duree(rendezVous.getDuree())
//                .build());
//    }
    @DeleteMapping("/deleteRDV/{id}")
    public ResponseEntity<String> deleteRendezVousById(

            @PathVariable("id") Integer id
    ) {
        rendezVousService.deleteRendezVousById(id);
        return ResponseEntity.ok("Rendez Vous id:" + id + " supprimé");
    }
//    @DeleteMapping("deleteRDV/{id}")
//    public ResponseEntity<String> deleteRendezVous(@PathVariable("id") Integer rendezVousId) {
//        rendezVousService.deleteRendezVous(rendezVousId);
//        return ResponseEntity.ok("Rendez-vous "+rendezVousId+" supprimé");
//    }

}