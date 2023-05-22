package info.cellardoor.CliniqueSolis.Comptabilite.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Devis")

public class Devis {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer devisId;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fournisseur_id")
    private  Fournisseur fournisseur;
    private String description ;
    private String montant;
    private String type_achat;
    private Status status ;

}
