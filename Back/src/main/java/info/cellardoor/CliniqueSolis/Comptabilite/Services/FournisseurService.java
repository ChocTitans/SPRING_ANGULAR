package info.cellardoor.CliniqueSolis.Comptabilite.Services;
import info.cellardoor.CliniqueSolis.Comptabilite.Http.Request.FournisseurRequest.FournisseurRequest;
import info.cellardoor.CliniqueSolis.Comptabilite.Http.Response.FournisseurResponse.FournisseurResponse;
import info.cellardoor.CliniqueSolis.Comptabilite.Http.Response.FournisseurResponse.ListFournisseurResponse;
import info.cellardoor.CliniqueSolis.Comptabilite.Models.Fournisseur;
import info.cellardoor.CliniqueSolis.Comptabilite.Models.repositories.FournisseurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
@Service
@RequiredArgsConstructor
public class FournisseurService {
    public final FournisseurRepository fournisseurRepository;
    public FournisseurResponse getFournisseurById(Integer id) {
        var fournisseur= fournisseurRepository.findById(id).orElseThrow(() ->new NoSuchElementException("non fournisseur trouve"));
        return FournisseurResponse.builder()
                .fournisseurId(fournisseur.getFournisseurId())
                .nom(fournisseur.getNom())
                .email(fournisseur.getEmail())
                .prenom(fournisseur.getPrenom())
                .nom_societe(fournisseur.getNom_societe())
                .build();
    }
    public FournisseurResponse createFournisseur(FournisseurRequest fournisseurRequest ){
        var fournisseur = Fournisseur.builder()
                        .nom(fournisseurRequest.getNom())
                                .prenom(fournisseurRequest.getPrenom())
                                        .email(fournisseurRequest.getEmail())
                                                .nom_societe(fournisseurRequest.getNom_societe()).build();
                
        fournisseurRepository.save(fournisseur);
        return FournisseurResponse.builder()
                .fournisseurId(fournisseur.getFournisseurId())
                .nom(fournisseur.getNom())
                .email(fournisseur.getEmail())
                .prenom(fournisseur.getPrenom())
                .nom_societe(fournisseur.getNom_societe())
                .build();

    }
    public FournisseurResponse deleteFournisseurById(Integer id) {
        var fournisseur= fournisseurRepository.findById(id).orElseThrow(() ->new NoSuchElementException("non fournisseur trouve"));
        fournisseurRepository.delete(fournisseur);
        return FournisseurResponse.builder()
                .fournisseurId(fournisseur.getFournisseurId())
                .nom(fournisseur.getNom())
                .email(fournisseur.getEmail())
                .prenom(fournisseur.getPrenom())
                .nom_societe(fournisseur.getNom_societe())
                .build();

    }
    public FournisseurResponse updateFournisseurById(Integer id, FournisseurRequest fournisseurRequest) {
        var fournisseur= fournisseurRepository.findById(id).orElseThrow(() ->new NoSuchElementException("non fournisseur trouve"));
        fournisseurRepository.save(fournisseur);
        return FournisseurResponse.builder()
                .fournisseurId(fournisseur.getFournisseurId())
                .nom(fournisseur.getNom())
                .email(fournisseur.getEmail())
                .prenom(fournisseur.getPrenom())
                .nom_societe(fournisseur.getNom_societe())
                .build();

    }

    public ListFournisseurResponse getAll() {
        var fournisseurs = fournisseurRepository.findAll();
        if (fournisseurs.size() == 0)
            return null;
        return ListFournisseurResponse.builder()
                .fournisseurs(fournisseurs.stream().map(fournisseur -> FournisseurResponse.builder()
                                .fournisseurId(fournisseur.getFournisseurId())
                                .nom(fournisseur.getNom())
                                .email(fournisseur.getEmail())
                                .prenom(fournisseur.getPrenom())
                                .nom_societe(fournisseur.getNom_societe())
                                .build())
                        .toList())
                .build();
    }
}
