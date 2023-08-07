package com.sisnat.api.app.animal.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sisnat.domain.*;
import com.sisnat.domain.animal.Animal;
import com.sisnat.domain.motivo.Motivo;
import com.sisnat.domain.nomeCientifico.NomeCientifico;
import com.sisnat.domain.nomePopular.NomePopular;
import com.sisnat.domain.procedencia.Procedencia;
import com.sisnat.domain.statusAnimal.StatusAnimal;
import com.sisnat.util.message.Message;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnimalDTO {

    private Long id;
    private ClassesAnimais classe;
    private NomeCientifico nomeCientifico;
    private NomePopular nomePopular;
    private SexoAnimal sexo;
    private Status status;
    private FaixaEtaria faixaEtaria;
    private EstadoGeral condicaoFisica;
    private Procedencia procedencia;
    private Motivo motivo;
    private StatusAnimal statusAnimal;
    private String bo;
    private String municipioDeOrigem;
    private String endereco;
    private String coordenadas;
    private String nomeDoador;
    private String telefone;
    private String areaDoResgate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dataEntrada;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dataNascimento;
    private String observacao;

    private Message mensagem;

    public AnimalDTO (final Animal animal){
        this.id = animal.getId();
        this.classe = animal.getClasse();
        this.nomeCientifico = animal.getNomeCientifico();
        this.nomePopular = animal.getNomePopular();
        this.sexo = animal.getSexo();
        this.status = animal.getStatus();
        this.faixaEtaria = animal.getFaixaEtaria();
        this.condicaoFisica = animal.getCondicaoFisica();
        this.procedencia = animal.getProcedencia();
        this.motivo = animal.getMotivo();
        this.statusAnimal = animal.getStatusAnimal();
        this.bo = animal.getBo();
        this.municipioDeOrigem = animal.getMunicipioDeOrigem();
        this.endereco = animal.getEndereco();
        this.coordenadas = animal.getCoordenadas();
        this.nomeDoador = animal.getNomeDoador();
        this.telefone = animal.getTelefone();
        this.areaDoResgate = animal.getAreaDoResgate();
        this.dataEntrada = animal.getDataEntrada();
        this.dataNascimento = animal.getDataNascimento();
        this.observacao = animal.getObservacao();
    }
}
