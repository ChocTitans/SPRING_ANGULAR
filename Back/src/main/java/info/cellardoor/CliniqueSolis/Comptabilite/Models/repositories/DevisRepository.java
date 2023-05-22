package info.cellardoor.CliniqueSolis.Comptabilite.Models.repositories;

import info.cellardoor.CliniqueSolis.Comptabilite.Controllers.DevisController;
import info.cellardoor.CliniqueSolis.Comptabilite.Models.Devis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DevisRepository extends JpaRepository<Devis, Integer> {
}
