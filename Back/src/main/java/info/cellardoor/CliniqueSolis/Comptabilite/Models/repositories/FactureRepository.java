package info.cellardoor.CliniqueSolis.Comptabilite.Models.repositories;

import info.cellardoor.CliniqueSolis.Comptabilite.Models.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureRepository extends JpaRepository<Facture, Integer> {
}
