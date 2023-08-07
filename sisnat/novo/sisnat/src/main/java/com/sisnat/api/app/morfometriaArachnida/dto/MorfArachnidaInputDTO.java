package com.sisnat.api.app.morfometriaArachnida.dto;

import com.sisnat.api.app.animal.dto.AnimalAtribuirDTO;
import com.sisnat.domain.morfometriaArachnida.MorfometriaArachnida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MorfArachnidaInputDTO {

    private Date dataMorf;
    private Double peso;
    private Double cc;
    private Double cp;
    private Double cpp;
    private String observacao;
    private AnimalAtribuirDTO animal;

    public MorfometriaArachnida toModel(){
        return MorfometriaArachnida
                .builder()
                .dataMorf(this.dataMorf)
                .peso(this.peso)
                .cc(this.cc)
                .cp(this.cp)
                .cpp(this.cpp)
                .observacao(this.observacao)
                .animal(this.animal.toModel())
                .build();
    }

}
