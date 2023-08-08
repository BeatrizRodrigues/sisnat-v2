package com.sisnat.domain.animal;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sisnat.domain.*;
import com.sisnat.domain.alimentacao.Alimentacao;
import com.sisnat.domain.atendimento.Atendimento;
import com.sisnat.domain.diario.Diario;
import com.sisnat.domain.ecdise.Ecdise;
import com.sisnat.domain.morfometriaAmphibia.MorfometriaAmphibia;
import com.sisnat.domain.morfometriaArachnida.MorfometriaArachnida;
import com.sisnat.domain.morfometriaAves.MorfometriaAves;
import com.sisnat.domain.morfometriaMammalia.MorfometriaMammalia;
import com.sisnat.domain.morfometriaReptilia.MorfometriaReptilia;
import com.sisnat.domain.motivo.Motivo;
import com.sisnat.domain.nomeCientifico.NomeCientifico;
import com.sisnat.domain.nomePopular.NomePopular;
import com.sisnat.domain.procedencia.Procedencia;
import com.sisnat.domain.statusAnimal.StatusAnimal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@GenericGenerator(
        name = "seqanimal",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @org.hibernate.annotations.Parameter(name = "sequence_name", value = "TB_ANIMAL_SEQ"),
                @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
        }
)
@Builder(toBuilder = true)
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_ANIMAL")
public class Animal implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator="seqanimal")
    @Column(name = "ANIMAL_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "CLASSE")
    private ClassesAnimais classe;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "NOME_CIENTIFICO")
    private NomeCientifico nomeCientifico;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "NOME_POPULAR")
    private NomePopular nomePopular;

    @Column(name = "SEXO")
    private SexoAnimal sexo;

    @Column(name = "STATUS_CADASTRO")
    private Status status;

    @Column(name = "FAIXA_ETARIA")
    private FaixaEtaria faixaEtaria;

    @Column(name = "CONDICAO_FISICA")
    private EstadoGeral condicaoFisica;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "PROCEDENCIA")
    private Procedencia procedencia;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "MOTIVO")
    private Motivo motivo;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "STATUS_ANIMAL")
    private StatusAnimal statusAnimal;

    @Column(name = "B_O")
    private String bo;

    @Column(name = "MUNICIPIO_ORIGEM")
    private String municipioDeOrigem;

    @Column(name = "ENDERECO")
    private String endereco;

    @Column(name = "COORDENADAS")
    private String coordenadas;

    @Column(name = "NOME_DOADOR")
    private String nomeDoador;

    @Column(name = "TELEFONE")
    private String telefone;

    @Column(name = "AREA_RESGATE")
    private String areaDoResgate;

    @Column(name = "DATA_ENTRADA")
    private Date dataEntrada;

    @Column(name = "DATA_NASCIMENTO")
    private Date dataNascimento;

    @Column(name = "OBSERVACAO")
    private String observacao;

    @OneToMany(mappedBy = "animal")
    @JsonBackReference
    private List<Alimentacao> alimentacao;

    @OneToMany(mappedBy = "animal")
    @JsonBackReference
    private List<Atendimento> atendimentos;

    @OneToMany(mappedBy = "animal")
    @JsonBackReference
    private List<Diario> diario;

    @OneToMany(mappedBy = "animal")
    @JsonBackReference
    private List<Ecdise> ecdises;

    @OneToMany(mappedBy = "animal")
    @JsonBackReference
    private List<MorfometriaReptilia> morfometriaReptilia;

    @OneToMany(mappedBy = "animal")
    @JsonBackReference
    private List<MorfometriaAmphibia> morfometriaAmphibia;

    @OneToMany(mappedBy = "animal")
    @JsonBackReference
    private List<MorfometriaArachnida> morfometriaArachnida;

    @OneToMany(mappedBy = "animal")
    @JsonBackReference
    private List<MorfometriaAves> morfometriaAves;

    @OneToMany(mappedBy = "animal")
    @JsonBackReference
    private List<MorfometriaMammalia> morfometriaMammalia;

}
