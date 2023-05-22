package info.cellardoor.CliniqueSolis.App.Seeders;

import com.github.javafaker.Faker;
import info.cellardoor.CliniqueSolis.App.Config.LocalizedFakerFrench;
import info.cellardoor.CliniqueSolis.Consultation.Models.Consultation;
import info.cellardoor.CliniqueSolis.Consultation.Models.ConsultationRepository;
import info.cellardoor.CliniqueSolis.Medecin.Models.Medecin;
import info.cellardoor.CliniqueSolis.Medecin.Models.MedecinRepository;
import info.cellardoor.CliniqueSolis.Patient.Models.Patient;
import info.cellardoor.CliniqueSolis.Patient.Models.PatientRepository;
import info.cellardoor.CliniqueSolis.RendezVous.Models.RendezVous;
import info.cellardoor.CliniqueSolis.RendezVous.Models.RendezVousRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;


@Component
@Order(5)
public class ConsultationSeeder implements CommandLineRunner {

    private final Faker frenchFaker = LocalizedFakerFrench.getInstance();

    private final ConsultationRepository consultationRepository;
    private final RendezVousRepository rendezVousRepository;

    public ConsultationSeeder(ConsultationRepository consultationRepository, RendezVousRepository rendezVousRepository) {
        this.consultationRepository = consultationRepository;
        this.rendezVousRepository = rendezVousRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        int nbConsultations = 5;

        for (int i = 0; i < nbConsultations; i++) {


            List<RendezVous> rendezVousList = rendezVousRepository.findAll();
            RendezVous rendezVous = rendezVousList.get(frenchFaker.number().numberBetween(0, rendezVousList.size()));

            // Créer une consultation
            Consultation consultation = Consultation.builder()
                    .consultationId(i + 1)
                    .rendezVousId(rendezVous)
                    .description(frenchFaker.lorem().sentence())
                    .dateConsultation(String.format("%02d-%02d-%02d", frenchFaker.number().numberBetween(2023, 2024), frenchFaker.number().numberBetween(1, 12), frenchFaker.number().numberBetween(1, 28)))
                    .build();
            consultationRepository.save(consultation);
        }
    }
}