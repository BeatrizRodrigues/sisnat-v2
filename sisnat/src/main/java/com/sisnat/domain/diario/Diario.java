package com.sisnat.domain.diario;

import com.sisnat.domain.animal.Animal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Date;

@GenericGenerator(
        name = "seqdiario",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @org.hibernate.annotations.Parameter(name = "sequence_name", value = "TB_DIARIO_SEQ"),
                @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
        }
)
@Builder(toBuilder = true)
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_DIARIO")
public class Diario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator="seqdiario")
    @Column(name = "DIARIO_ID")
    private Long id;

    @Column(name = "DATA_DIARIO")
    private Date dataDiario;

    @Column(name = "OBSERVACAO")
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "ANIMAL")
    private Animal animal;
}
