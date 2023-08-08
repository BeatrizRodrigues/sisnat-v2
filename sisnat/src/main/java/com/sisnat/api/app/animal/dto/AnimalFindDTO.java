package com.sisnat.api.app.animal.dto;

import com.sisnat.domain.*;
import com.sisnat.domain.animal.Animal;
import com.sisnat.domain.nomeCientifico.NomeCientifico;
import com.sisnat.domain.nomePopular.NomePopular;
import com.sisnat.domain.statusAnimal.StatusAnimal;
import com.sisnat.util.pagination.FindAbstract;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalFindDTO extends FindAbstract<Animal> {

    private ClassesAnimais classe;
    private NomeCientifico nomeCientifico;
    private NomePopular nomePopular;
    private SexoAnimal sexo;
    private Status status;
    private StatusAnimal statusAnimal;
    private String municipioDeOrigem;
    private Date dataEntrada;
    private Date dataNascimento;

    @Override
    protected Animal getProbe() {
        return Animal.builder()
                .classe(this.classe)
                .nomeCientifico(this.nomeCientifico)
                .nomePopular(this.nomePopular)
                .sexo(this.sexo)
                .status(this.status)
                .statusAnimal(this.statusAnimal)
                .municipioDeOrigem(this.municipioDeOrigem)
                .dataEntrada(this.dataEntrada)
                .dataNascimento(this.dataNascimento)
                .build();
    }
}
