package info.cellardoor.CliniqueSolis.App.Seeders;

import com.github.javafaker.Faker;
import info.cellardoor.CliniqueSolis.App.Config.LocalizedFakerFrench;
import info.cellardoor.CliniqueSolis.Auth.Models.User.Roles;
import info.cellardoor.CliniqueSolis.Auth.Models.User.User;
import info.cellardoor.CliniqueSolis.Medecin.Models.Medecin;
import info.cellardoor.CliniqueSolis.Medecin.Models.MedecinRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class MedecinSeeder implements CommandLineRunner {
    private final MedecinRepository medecinRepository;
    private final Faker frenchFaker = LocalizedFakerFrench.getInstance();

    public MedecinSeeder(MedecinRepository medecinRepository) {
        this.medecinRepository = medecinRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        int nbMedecins = 5;

        for (int i = 0; i < nbMedecins; i++) {
            User user = UserSeeder.getSeed(frenchFaker);
            user.setRole(Roles.ROLE_MEDECIN);

            Medecin medecin = getSeed(frenchFaker, user);

            medecinRepository.save(medecin);

        }
    }

    static Medecin getSeed(Faker faker, User user) {
        String specialite = faker.options()
                .option("Cardiologue", "Dentiste", "Généraliste", "Gynécologue", "Neurologue",
                        "Ophtalmologue", "Orthopédiste", "Pédiatre", "Psychiatre", "Radiologue", "Urologue");
        String cin = faker.regexify("[A-Z]{2}[0-9]{5}");
        String diplome = faker.university().name();

        return Medecin.builder()
                .user(user)
                .specialite(specialite)
                .cin(cin)
                .diplome(diplome)
                .build();
    }
}