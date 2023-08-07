package com.sisnat.api.app.morfometriaAve.dto;

import com.sisnat.api.app.animal.dto.AnimalAtribuirDTO;
import com.sisnat.domain.morfometriaAves.MorfometriaAves;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MorfAveInputDTO {

    private Date dataMorf;
    private Double peso;
    private Double cb;
    private Double h;
    private Double cc;
    private Double ca;
    private Double ct;
    private String observacao;
    private AnimalAtribuirDTO animal;

    public MorfometriaAves toModel(){
        return MorfometriaAves
                .builder()
                .dataMorf(this.dataMorf)
                .peso(this.peso)
                .cb(this.cb)
                .cc(this.cc)
                .h(this.h)
                .ca(this.ca)
                .ct(this.ct)
                .observacao(this.observacao)
                .animal(this.animal.toModel())
                .build();
    }
}
