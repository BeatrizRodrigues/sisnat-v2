package com.sisnat.domain.morfometriaAmphibia;

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
        name = "seqmorfamphibia",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @org.hibernate.annotations.Parameter(name = "sequence_name", value = "TB_MORFOMETRIA_AMPHIBIA_SEQ"),
                @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
        }
)
@Builder(toBuilder = true)
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_MORFOMETRIA_AMPHIBIA")
public class MorfometriaAmphibia implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator="seqmorfamphibia")
    @Column(name = "MORF_AMPHIBIA_ID")
    private Long id;

    @Column(name = "DATA_MORF")
    @Temporal(TemporalType.DATE)
    private Date dataMedicao;

    @Column(name = "PESO")
    private Double peso;

    @Column(name = "CRC")
    private Double crc;

    @Column(name = "CC")
    private Double cc;

    @Column(name = "DON")
    private Double don;

    @Column(name = "CF")
    private Double cf;

    @Column(name = "CT")
    private Double ct;

    @Column(name = "CTA")
    private Double cta;

    @Column(name = "CP")
    private Double cp;

    @Column(name = "OBSERVACAO")
    private String observacao;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "ANIMAL")
    private Animal animal;

}
