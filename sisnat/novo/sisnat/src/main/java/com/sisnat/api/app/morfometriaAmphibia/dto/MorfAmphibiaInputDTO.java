package com.sisnat.api.app.morfometriaAmphibia.dto;

import com.sisnat.api.app.animal.dto.AnimalAtribuirDTO;
import com.sisnat.domain.morfometriaAmphibia.MorfometriaAmphibia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MorfAmphibiaInputDTO {

    private Date dataMedicao;
    private Double peso;
    private Double crc;
    private Double cc;
    private Double don;
    private Double cf;
    private Double ct;
    private Double cta;
    private Double cp;
    private String observacao;
    private AnimalAtribuirDTO animal;

    public MorfometriaAmphibia toModel(){
        return MorfometriaAmphibia
                .builder()
                .dataMedicao(this.dataMedicao)
                .peso(this.peso)
                .crc(this.crc)
                .cc(this.cc)
                .don(this.don)
                .cf(this.cf)
                .ct(this.ct)
                .cta(this.cta)
                .cp(this.cp)
                .observacao(this.observacao)
                .animal(this.animal.toModel())
                .build();
    }
}
