package info.cellardoor.CliniqueSolis.Comptabilite.Models.repositories;

import info.cellardoor.CliniqueSolis.Comptabilite.Controllers.DevisController;
import info.cellardoor.CliniqueSolis.Comptabilite.Models.Fournisseur;
import info.cellardoor.CliniqueSolis.Patient.Models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FournisseurRepository  extends JpaRepository<Fournisseur, Integer> {
    Optional<Fournisseur> findByNom(String nom);
}
