package info.cellardoor.CliniqueSolis.App.Seeders;


import com.github.javafaker.Faker;
import info.cellardoor.CliniqueSolis.App.Config.LocalizedFakerFrench;
import info.cellardoor.CliniqueSolis.Auth.Models.User.User;
import info.cellardoor.CliniqueSolis.Auth.Models.User.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class UserSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final Faker frenchFaker = LocalizedFakerFrench.getInstance();
    private final User admin = User.getAdminInstance("Elliot", "Alderson", "e.alderson@solis.ma", "123456");
    private final User defaultUser = User.getUserInstance("Laouina", "Yassine", "l.yassine@solis.ma", "123456");

    public UserSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        userRepository.save(admin);
        userRepository.save(defaultUser);

        int nbUsers = 5;

        for (int i = 0; i < nbUsers; i++) {
            User user = getSeed(frenchFaker);
            userRepository.save(user);
        }
    }

    static User getSeed(Faker faker) {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@solis.ma";
        String password = new BCryptPasswordEncoder().encode("123456");
        return new User(firstName, lastName, email, password);
    }
}