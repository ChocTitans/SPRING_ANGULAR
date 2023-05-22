package info.cellardoor.CliniqueSolis.Comptabilite.Services;
import info.cellardoor.CliniqueSolis.Comptabilite.Http.Request.DevisRequest.DevisRequest;
import info.cellardoor.CliniqueSolis.Comptabilite.Http.Response.DevisResponse.DevisResponse;
import info.cellardoor.CliniqueSolis.Comptabilite.Models.Devis;
import info.cellardoor.CliniqueSolis.Comptabilite.Models.repositories.DevisRepository;
import info.cellardoor.CliniqueSolis.Comptabilite.Models.repositories.FournisseurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
@RequiredArgsConstructor
@Service

public class DevisService {
    public final DevisRepository devisRepository;
    public final FournisseurRepository fournisseurRepository;

    public DevisResponse getDevisById(Integer id) {
        var devis= devisRepository.findById(id).orElseThrow(() ->new NoSuchElementException("non devis trouve"));
        return DevisResponse.builder()
                .status(devis.getStatus())
                .description(devis.getDescription())
                .montant(devis.getMontant())
                .type_achat(devis.getType_achat())
                .fournisseur_name(devis.getFournisseur().getNom())
                .build();

    }
    public DevisResponse createDevis(DevisRequest devisRequest ){
        var fournisseur= fournisseurRepository.findByNom(devisRequest.getFournisseur_name()).orElseThrow(() ->new NoSuchElementException("non fournisseur trouve"));
        var devis = Devis.builder()
                .status(devisRequest.getStatus())
                .description(devisRequest.getDescription())
                .montant(devisRequest.getMontant())
                .type_achat(devisRequest.getType_achat())
                .fournisseur(fournisseur).build();
        devisRepository.save(devis);
        return DevisResponse.builder()
                .status(devis.getStatus())
                .description(devis.getDescription())
                .montant(devis.getMontant())
                .type_achat(devis.getType_achat())
                .fournisseur_name(devis.getFournisseur().getNom())
                .build();

    }
    public DevisResponse deleteFactureById(Integer id) {
        var devis= devisRepository.findById(id).orElseThrow(() ->new NoSuchElementException("non devis trouve"));
        devisRepository.delete(devis);
        return DevisResponse.builder()
                .status(devis.getStatus())
                .description(devis.getDescription())
                .montant(devis.getMontant())
                .type_achat(devis.getType_achat())
                .fournisseur_name(devis.getFournisseur().getNom())
                .build();

    }
    public DevisResponse updateDevisById(Integer id, DevisRequest devisRequest) {
        var devis= devisRepository.findById(id).orElseThrow(() ->new NoSuchElementException("non devis trouve"));
        devis.setMontant(devisRequest.getMontant());
        devis.setType_achat(devisRequest.getType_achat());
        devis.setDescription(devisRequest.getDescription());
        devis.setStatus(devisRequest.getStatus());
        devisRepository.save(devis);
        return DevisResponse.builder()
                .status(devis.getStatus())
                .description(devis.getDescription())
                .montant(devis.getMontant())
                .type_achat(devis.getType_achat())
                .fournisseur_name(devis.getFournisseur().getNom())
                .build();

    }


}




