package info.cellardoor.CliniqueSolis.Consultation.Models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConsultationRepository extends JpaRepository<Consultation, Integer> {
     Optional<Consultation> findByConsultationId(Integer consultationId);
     Optional<Consultation> findByRendezVousId(Integer rendezVousId);
}
