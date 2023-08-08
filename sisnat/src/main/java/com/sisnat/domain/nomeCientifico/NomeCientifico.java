package com.sisnat.domain.nomeCientifico;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sisnat.domain.animal.Animal;
import com.sisnat.domain.nomePopular.NomePopular;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.List;

@GenericGenerator(
        name = "seqnomecientifico",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @org.hibernate.annotations.Parameter(name = "sequence_name", value = "TB_NOME_CIENTIFICO_SEQ"),
                @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
        }
)
@Builder(toBuilder = true)
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_NOME_CIENTIFICO")
public class NomeCientifico implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator="seqnomecientifico")
    @Column(name = "NOME_CIENTIFICO_ID")
    private Long id;

    @Column(name = "NOME_CIENTIFICO")
    private String nomeCientifico;

    @OneToMany(mappedBy = "nomeCientifico")
    @JsonBackReference
    private List<NomePopular> nomePopular;

    @OneToMany(mappedBy = "nomeCientifico")
    @JsonBackReference
    private List<Animal> animal;
}
