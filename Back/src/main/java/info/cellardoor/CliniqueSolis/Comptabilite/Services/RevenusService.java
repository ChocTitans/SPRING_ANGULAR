package info.cellardoor.CliniqueSolis.Comptabilite.Services;


import info.cellardoor.CliniqueSolis.Comptabilite.Http.Response.FactureResponse.FactureResponse;
import info.cellardoor.CliniqueSolis.Comptabilite.Http.Response.RevenuResponse.RevenuResponse;
import info.cellardoor.CliniqueSolis.Comptabilite.Models.repositories.FactureRepository;
import info.cellardoor.CliniqueSolis.Comptabilite.Models.repositories.RevenusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class RevenusService {
    private final FactureRepository factureRepository;
    private final RevenusRepository revenusRepository;


    public RevenuResponse getAll() {
        var factures = factureRepository.findAll();
        if (factures.size() == 0)
            return null;
        return RevenuResponse.builder()
                .factures(factures.stream().map(facture -> FactureResponse.builder()
                                .factureId(facture.getFactureId())
                                .montant(facture.getMontant())
                                .cin(facture.getPatient().getCin())
                                .type_service(facture.getType_service())
                                .build())
                        .toList())
                .build();
    }
}
