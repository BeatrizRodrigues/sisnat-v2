package com.sisnat.domain.procedencia;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sisnat.domain.animal.Animal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.List;

@GenericGenerator(
        name = "seqprocedencia",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @org.hibernate.annotations.Parameter(name = "sequence_name", value = "TB_PROCEDENCIA_SEQ"),
                @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
        }
)
@Builder(toBuilder = true)
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_PROCEDENCIA")
public class Procedencia implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator="seqprocedencia")
    @Column(name = "PROCEDENCIA_ID")
    private Long id;

    @Column(name = "PROCEDENCIA")
    private String procedencia;

    @OneToMany(mappedBy = "procedencia")
    @JsonBackReference
    private List<Animal> animal;
}
