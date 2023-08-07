package com.sisnat.api.app.animal.dto;

import com.sisnat.api.app.motivo.dto.MotivoAtribuirDTO;
import com.sisnat.api.app.nomeCientifico.dto.NomeCientificoAtribuirDTO;
import com.sisnat.api.app.nomePopular.dto.NomePopularAtribuirDTO;
import com.sisnat.api.app.procedencia.dto.ProcedenciaAtribuirDTO;
import com.sisnat.api.app.statusAnimal.dto.StatusAnimalAtribuirDTO;
import com.sisnat.domain.*;
import com.sisnat.domain.animal.Animal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalInputDTO {

    private ClassesAnimais classe;
    private NomeCientificoAtribuirDTO nomeCientifico;
    private NomePopularAtribuirDTO nomePopular;
    private SexoAnimal sexo;
    private Status status;
    private FaixaEtaria faixaEtaria;
    private EstadoGeral condicaoFisica;
    private ProcedenciaAtribuirDTO procedencia;
    private MotivoAtribuirDTO motivo;
    private StatusAnimalAtribuirDTO statusAnimal;
    private String bo;
    private String municipioDeOrigem;
    private String endereco;
    private String coordenadas;
    private String nomeDoador;
    private String telefone;
    private String areaDoResgate;
    private Date dataEntrada;
    private Date dataNascimento;
    private String observacao;

    public Animal toModel() {
        return Animal
                .builder()
                .classe(this.classe)
                .nomeCientifico(this.nomeCientifico.toModel())
                .nomePopular(this.nomePopular.toModel())
                .sexo(this.sexo)
                .status(this.status)
                .faixaEtaria(this.faixaEtaria)
                .condicaoFisica(this.condicaoFisica)
                .procedencia(this.procedencia.toModel())
                .motivo(this.motivo.toModel())
                .statusAnimal(this.statusAnimal.toModel())
                .bo(this.bo)
                .municipioDeOrigem(this.municipioDeOrigem)
                .endereco(this.endereco)
                .coordenadas(this.coordenadas)
                .nomeDoador(this.nomeDoador)
                .telefone(this.telefone)
                .areaDoResgate(this.areaDoResgate)
                .dataEntrada(this.dataEntrada)
                .dataNascimento(this.dataNascimento)
                .observacao(this.observacao)
                .build();
    }

}
