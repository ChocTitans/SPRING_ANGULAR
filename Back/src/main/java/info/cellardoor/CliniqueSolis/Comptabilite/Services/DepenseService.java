package info.cellardoor.CliniqueSolis.Comptabilite.Services;

import info.cellardoor.CliniqueSolis.Comptabilite.Http.Response.DepenseResponse.DepenseResponse;
import info.cellardoor.CliniqueSolis.Comptabilite.Http.Response.DevisResponse.DevisResponse;
import info.cellardoor.CliniqueSolis.Comptabilite.Models.repositories.DevisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepenseService {
   private final DevisRepository devisRepository;
    public DepenseResponse getAll() {
        var deviss = devisRepository.findAll();
        if (deviss.size() == 0)
            return null;
        return DepenseResponse.builder()
                .devis(deviss.stream().map(devis -> DevisResponse.builder()
                        .status(devis.getStatus())
                        .description(devis.getDescription())
                        .montant(devis.getMontant())
                        .type_achat(devis.getType_achat())
                        .fournisseur_name(devis.getFournisseur().getNom())
                        .build())
                        .toList())
                .build();
    }


}
