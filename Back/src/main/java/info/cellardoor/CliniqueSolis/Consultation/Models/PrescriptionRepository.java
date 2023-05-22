package info.cellardoor.CliniqueSolis.Consultation.Models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrescriptionRepository extends JpaRepository<Prescription, Integer> {
    Optional<Prescription> findByPrescriptionId(Integer prescriptionId);
    Optional<Prescription> findByConsultationId(Integer consultationId);
}
