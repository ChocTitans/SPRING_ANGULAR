package info.cellardoor.CliniqueSolis.Comptabilite.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "revenus")

public class Revenu {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer revenuId;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Facture> factures;


}
