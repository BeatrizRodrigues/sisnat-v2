package com.sisnat.api.app.morfometriaMammalia.dto;

import com.sisnat.api.app.animal.dto.AnimalAtribuirDTO;
import com.sisnat.domain.morfometriaMammalia.MorfometriaMammalia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MorfMammaliaInputDTO {

    private Date dataMorf;
    private Double peso;
    private Double cra;
    private Double cc;
    private Double cm;
    private Double cp;
    private Double ho;
    private String observacao;
    private AnimalAtribuirDTO animal;

    public MorfometriaMammalia toModel(){
        return MorfometriaMammalia
                .builder()
                .dataMorf(this.dataMorf)
                .peso(this.peso)
                .cra(this.cra)
                .cc(this.cc)
                .cm(this.cm)
                .cp(this.cp)
                .ho(this.ho)
                .observacao(this.observacao)
                .animal(this.animal.toModel())
                .build();
    }
}
