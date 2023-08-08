package com.sisnat.api.app.morfometriaReptilia.dto;

import com.sisnat.api.app.animal.dto.AnimalAtribuirDTO;
import com.sisnat.domain.morfometriaReptilia.MorfometriaReptilia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MorfReptiliaInputDTO {

    private Date dataMorf;
    private Double peso;
    private Double crc;
    private String observacao;
    private AnimalAtribuirDTO animal;

    public MorfometriaReptilia toModel(){
        return MorfometriaReptilia
                .builder()
                .dataMorf(this.dataMorf)
                .peso(this.peso)
                .crc(this.crc)
                .observacao(this.observacao)
                .animal(this.animal.toModel())
                .build();
    }
}
