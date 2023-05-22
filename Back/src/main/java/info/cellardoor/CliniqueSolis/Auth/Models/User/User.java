package info.cellardoor.CliniqueSolis.Auth.Models.User;


import info.cellardoor.CliniqueSolis.Auth.Models.Token.Token;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id @GeneratedValue
    private Integer userId;
    private String nom;
    private String prenom;
    private String email;
    private String mdp;
    @Enumerated(EnumType.STRING)
    private Roles role;

    public User(String nom, String prenom, String email, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.role = Roles.ROLE_UTILISATEUR;
    }

    private static User adminInstance = null;
    
    public Boolean getStatus() {
        return tokens.stream().anyMatch(
                token -> (!token.expired && !token.revoked)
        );
    }

    public static synchronized User getAdminInstance(String nom, String prenom, String email, String mdp) {
        if (adminInstance == null) {
            adminInstance = new User(nom, prenom, email, new BCryptPasswordEncoder().encode(mdp));
            adminInstance.setRole(Roles.ROLE_ADMIN);
        }
        return adminInstance;
    }

    public static synchronized User getUserInstance(String nom, String prenom, String email, String mdp) {
        return new User(nom, prenom, email, new BCryptPasswordEncoder().encode(mdp));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Override
    public String getPassword() {
        return mdp;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}