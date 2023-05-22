package info.cellardoor.CliniqueSolis.App.Seeders;

import com.github.javafaker.Faker;
import info.cellardoor.CliniqueSolis.App.Config.LocalizedFakerFrench;
import info.cellardoor.CliniqueSolis.Auth.Models.User.Roles;
import info.cellardoor.CliniqueSolis.Auth.Models.User.User;
import info.cellardoor.CliniqueSolis.Medecin.Models.Medecin;
import info.cellardoor.CliniqueSolis.Patient.Models.Patient;
import info.cellardoor.CliniqueSolis.RendezVous.Models.RendezVous;
import info.cellardoor.CliniqueSolis.RendezVous.Models.RendezVousRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(4)
public class RendezVousSeeder implements CommandLineRunner {
    private final Faker frenchFaker = LocalizedFakerFrench.getInstance();

    private final RendezVousRepository rendezVousRepository;

    public RendezVousSeeder(RendezVousRepository rendezVousRepository) {
        this.rendezVousRepository = rendezVousRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        int nbRendezVous = 5;

        for (int i = 0; i < nbRendezVous; i++) {

            User user_patient = UserSeeder.getSeed(frenchFaker);
            user_patient.setRole(Roles.ROLE_PATIENT);
            Patient patient = PatientSeeder.getSeed(frenchFaker, user_patient);

            User user_medecin = UserSeeder.getSeed(frenchFaker);
            user_medecin.setRole(Roles.ROLE_MEDECIN);
            Medecin medecin = MedecinSeeder.getSeed(frenchFaker, user_medecin);

            RendezVous rdv = RendezVous.builder()
                    .patient(patient)
                    .medecin(medecin)
                    .duree(frenchFaker.options().option(15, 30, 45, 60))
                    .date(String.format("%02d-%02d-%02d", frenchFaker.number().numberBetween(2023, 2024), frenchFaker.number().numberBetween(1, 12), frenchFaker.number().numberBetween(1, 28)))
                    .heure(String.format("%02d:%02d", frenchFaker.number().numberBetween(8, 18), frenchFaker.options().option(0, 30)))
                    .build();
            rendezVousRepository.save(rdv);
        }
    }
}