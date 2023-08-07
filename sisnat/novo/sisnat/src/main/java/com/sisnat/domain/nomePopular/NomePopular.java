package com.sisnat.domain.nomePopular;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sisnat.domain.animal.Animal;
import com.sisnat.domain.nomeCientifico.NomeCientifico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.List;

@GenericGenerator(
        name = "seqnomepopular",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @org.hibernate.annotations.Parameter(name = "sequence_name", value = "TB_NOME_POPULAR_SEQ"),
                @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
        }
)
@Builder(toBuilder = true)
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_NOME_POPULAR")
public class NomePopular implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator="seqnomepopular")
    @Column(name = "NOME_POPULAR_ID")
    private Long id;

    @Column(name = "NOME_POPULAR")
    private String nomePopular;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "NOME_CIENTIFICO")
    private NomeCientifico nomeCientifico;

    @OneToMany(mappedBy = "nomePopular")
    @JsonBackReference
    private List<Animal> animal;

}
