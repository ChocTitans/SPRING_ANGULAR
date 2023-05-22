package info.cellardoor.CliniqueSolis.Medecin.Models;

import info.cellardoor.CliniqueSolis.Auth.Models.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Medecins")
public class Medecin{
    @Id @GeneratedValue
    private Integer medecinId;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    public User user;
    private String specialite;
    private String diplome;
    private String cin;
}