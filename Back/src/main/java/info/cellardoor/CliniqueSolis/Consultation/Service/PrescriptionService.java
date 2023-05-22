package info.cellardoor.CliniqueSolis.Consultation.Service;

import info.cellardoor.CliniqueSolis.Consultation.Http.Request.PrescriptionRequest;
import info.cellardoor.CliniqueSolis.Consultation.Http.Response.PrescriptionResponse;
import info.cellardoor.CliniqueSolis.Consultation.Models.Consultation;
import info.cellardoor.CliniqueSolis.Consultation.Models.ConsultationRepository;
import info.cellardoor.CliniqueSolis.Consultation.Models.Prescription;
import info.cellardoor.CliniqueSolis.Consultation.Models.PrescriptionRepository;
import info.cellardoor.CliniqueSolis.RendezVous.Models.RendezVous;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
public class PrescriptionService {
    public final PrescriptionRepository prescriptionRepository;
    public final ConsultationRepository consultationRepository;

    @Autowired
    public PrescriptionService(ConsultationRepository consultationRepository,PrescriptionRepository prescriptionRepository ) {
        this.prescriptionRepository = prescriptionRepository;
        this.consultationRepository = consultationRepository;
    }
    private Consultation getConsultationById(Integer consultationId) {
        Optional<Consultation> consultation = consultationRepository.findById(consultationId);
        if (consultation.isEmpty()) {
            throw new IllegalArgumentException("Consultation not found");
        }
        return consultation.get();
    }
    public PrescriptionResponse getPrescriptionById(Integer id) {
        var prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Prescription not found"));
        return PrescriptionResponse.builder()
                .prescriptionId(prescription.getPrescriptionId())
                .medicament(prescription.getMedicament())
                .duree(prescription.getDuree())
                .build();
    }

    public PrescriptionResponse createPrescription(PrescriptionRequest prescriptionRequest) {
        Consultation consultation = getConsultationById(prescriptionRequest.getConsultationId());
        var prescription = Prescription.builder()
                .prescriptionId(prescriptionRequest.getPrescriptionId())
                .consultationId(consultation)
                .medicament(prescriptionRequest.getMedicament())
                .duree(prescriptionRequest.getDuree())
                .build();
        prescriptionRepository.save(prescription);
        return PrescriptionResponse.builder()
                .prescriptionId(prescription.getPrescriptionId())
                .consultationId(prescription.getConsultationId().getConsultationId())
                .medicament(prescription.getMedicament())
                .duree(prescription.getDuree())
                .build();
    }

    public PrescriptionResponse updatePrescriptionById(Integer id, PrescriptionRequest prescriptionRequest) {
        var prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Prescription not found"));
        Consultation consultation = getConsultationById(prescriptionRequest.getConsultationId());
        prescription.setPrescriptionId(prescriptionRequest.getPrescriptionId());
        prescription.setConsultationId(consultation);
        prescription.setMedicament(prescriptionRequest.getMedicament());
        prescription.setDuree(prescriptionRequest.getDuree());
        var savedPrescription= prescriptionRepository.save(prescription);
        return PrescriptionResponse.builder()
                .prescriptionId(prescription.getPrescriptionId())
                .consultationId(prescription.getConsultationId().getConsultationId())
                .medicament(prescription.getMedicament())
                .duree(prescription.getDuree())
                .build();
    }

    public void deletePrescriptionById(Integer prescriptionId) {
        var prescription = prescriptionRepository.findByPrescriptionId(prescriptionId)
                .orElseThrow(() -> new NoSuchElementException("Prescription non trouvé"));
        prescriptionRepository.delete(prescription);
    }
}
