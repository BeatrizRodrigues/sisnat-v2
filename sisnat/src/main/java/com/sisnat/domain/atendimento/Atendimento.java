package com.sisnat.domain.atendimento;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
        name = "seqatendimento",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @org.hibernate.annotations.Parameter(name = "sequence_name", value = "TB_ATENDIMENTO_SEQ"),
                @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
        }
)
@Builder(toBuilder = true)
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_ATENDIMENTO")
public class Atendimento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator="seqatendimento")
    @Column(name = "ATENDIMENTO_ID")
    private Long id;

    @Column(name = "DATA_PROCEDIMENTO")
    private Date dataProcedimento;

    @Column(name = "PROCEDIMENTO")
    private String procedimento;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "ANIMAL")
    private Animal animal;
}
