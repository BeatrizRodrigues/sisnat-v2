package com.sisnat.domain.morfometriaReptilia;

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
        name = "seqmorfreptilia",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @org.hibernate.annotations.Parameter(name = "sequence_name", value = "TB_MORFOMETRIA_REPTILIA_SEQ"),
                @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
        }
)
@Builder(toBuilder = true)
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_MORFOMETRIA_REPTILIA")
public class MorfometriaReptilia implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator="seqmorfreptilia")
    @Column(name = "MORF_REPTILIA_ID")
    private Long id;

    @Column(name = "DATA_MORF")
    private Date dataMorf;

    @Column(name = "PESO")
    private Double peso;

    @Column(name = "CRC")
    private Double crc;

    @Column(name = "OBSERVACAO")
    private String observacao;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "ANIMAL")
    private Animal animal;

}
