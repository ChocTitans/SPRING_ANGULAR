package info.cellardoor.CliniqueSolis.Comptabilite.Models;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data

@Entity
@Transactional
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "Depenses")

public class Depense {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer depenseId;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "devisId")
    private List<Devis> devisList ;




}
