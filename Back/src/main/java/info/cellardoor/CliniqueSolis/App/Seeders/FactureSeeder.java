package info.cellardoor.CliniqueSolis.App.Seeders;

import com.github.javafaker.Faker;
import info.cellardoor.CliniqueSolis.App.Config.LocalizedFakerFrench;
import info.cellardoor.CliniqueSolis.Auth.Models.User.Roles;
import info.cellardoor.CliniqueSolis.Auth.Models.User.User;
import info.cellardoor.CliniqueSolis.Comptabilite.Models.Facture;
import info.cellardoor.CliniqueSolis.Comptabilite.Models.repositories.FactureRepository;
import info.cellardoor.CliniqueSolis.Medecin.Models.Medecin;
import info.cellardoor.CliniqueSolis.Patient.Models.Patient;
import info.cellardoor.CliniqueSolis.Patient.Models.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(5)
public class FactureSeeder implements CommandLineRunner {

    private final FactureRepository factureRepository;
    private final Faker frenchFaker = LocalizedFakerFrench.getInstance();

    public FactureSeeder(FactureRepository factureRepository) {
        this.factureRepository = factureRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        int nbFactures = 5;

        for (int i = 0; i < nbFactures; i++) {
            User user_patient = UserSeeder.getSeed(frenchFaker);
            user_patient.setRole(Roles.ROLE_PATIENT);
            Patient patient = PatientSeeder.getSeed(frenchFaker, user_patient);
            Facture facture = getSeed(frenchFaker, patient);

            factureRepository.save(facture);
        }
    }

    static Facture getSeed(Faker faker, Patient patient) {
        String type_service = faker.lorem().sentence();
        Long montant = (long) faker.number().randomDouble(2, 0, 30000);

        return Facture.builder()
                .patient(patient)
                .montant(montant)
                .type_service(type_service)
                .build();
    }
}