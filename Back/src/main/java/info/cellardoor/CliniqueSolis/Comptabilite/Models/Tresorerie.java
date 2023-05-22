package info.cellardoor.CliniqueSolis.Comptabilite.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Tresorerie")

public class Tresorerie {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer tresorerieId;
    public Long capital ;


}

