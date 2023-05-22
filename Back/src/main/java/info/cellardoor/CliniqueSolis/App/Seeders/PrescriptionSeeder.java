package info.cellardoor.CliniqueSolis.App.Seeders;

import com.github.javafaker.Faker;
import info.cellardoor.CliniqueSolis.App.Config.LocalizedFakerFrench;
import info.cellardoor.CliniqueSolis.Consultation.Models.Consultation;
import info.cellardoor.CliniqueSolis.Consultation.Models.ConsultationRepository;
import info.cellardoor.CliniqueSolis.Consultation.Models.Prescription;
import info.cellardoor.CliniqueSolis.Consultation.Models.PrescriptionRepository;
import info.cellardoor.CliniqueSolis.RendezVous.Models.RendezVous;
import info.cellardoor.CliniqueSolis.RendezVous.Models.RendezVousRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(5)
public class PrescriptionSeeder implements CommandLineRunner {
    private final Faker frenchFaker = LocalizedFakerFrench.getInstance();

    private final ConsultationRepository consultationRepository;
    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionSeeder(ConsultationRepository consultationRepository, PrescriptionRepository prescriptionRepository) {
        this.consultationRepository = consultationRepository;
        this.prescriptionRepository = prescriptionRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        int nbConsultations = 5;

        for (int i = 0; i < nbConsultations; i++) {


            List<Consultation> consultationList = consultationRepository.findAll();
            Consultation consultation = consultationList.get(frenchFaker.number().numberBetween(0, consultationList.size()));

            // Créer une consultation
            Prescription prescription = Prescription.builder()
                    .prescriptionId(i + 1)
                    .consultationId(consultation)
                    .medicament(frenchFaker.lorem().sentence())
                    .duree(frenchFaker.number().numberBetween(1, 30))
                    .build();
            prescriptionRepository.save(prescription);
        }
    }
}
