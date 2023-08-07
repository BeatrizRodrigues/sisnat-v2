package com.sisnat.domain.ecdise;

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
        name = "seqecdise",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @org.hibernate.annotations.Parameter(name = "sequence_name", value = "TB_ECDISE_SEQ"),
                @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
        }
)
@Builder(toBuilder = true)
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_ECDISE")
public class Ecdise implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator="seqecdise")
    @Column(name = "ECDISE_ID")
    private Long id;

    @Column(name = "DATA_ECDISE")
    private Date dataEcdise;

    @Column(name = "ECDISE")
    private String ecdise;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "ANIMAL")
    private Animal animal;
}
