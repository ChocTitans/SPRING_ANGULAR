package info.cellardoor.CliniqueSolis.Comptabilite.Models;

import info.cellardoor.CliniqueSolis.Auth.Models.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "Fournisseurs")

public class Fournisseur {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer fournisseurId;
    private String nom;
    private String prenom;
    private String nom_societe;
    private String email;
}
