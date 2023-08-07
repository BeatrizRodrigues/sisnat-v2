package com.sisnat.domain.alimentacao;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sisnat.domain.animal.Animal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@GenericGenerator(
        name = "seqalimentacao",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @org.hibernate.annotations.Parameter(name = "sequence_name", value = "TB_ALIMENTACAO_SEQ"),
                @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
        }
)
@Builder(toBuilder = true)
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_ALIMENTACAO")
public class Alimentacao implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator="seqalimentacao")
    @Column(name = "ID_ALIMENTACAO")
    private Long id;

    @Column(name = "DATA_ALIMENTACAO")
    @Temporal(TemporalType.DATE)
    private Date dataAlimentacao;

    @Column(name = "ALIMENTO")
    private String alimento;

    @Column(name = "QUANTIDADE")
    private BigDecimal quantidade;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "ANIMAL")
    private Animal animal;

}
