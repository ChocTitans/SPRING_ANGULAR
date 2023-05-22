package info.cellardoor.CliniqueSolis.Medecin.Models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MedecinRepository extends JpaRepository<Medecin, Integer> {

    Optional<Medecin> findByMedecinId(Integer medecinId);

    List<Medecin> findByCinStartingWith(String cin);
}