package com.sisnat.api.app.diario.dto;

import com.sisnat.api.app.animal.dto.AnimalAtribuirDTO;
import com.sisnat.domain.diario.Diario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiarioInputDTO {

    private Date dataDiario;
    private String observacao;
    private AnimalAtribuirDTO animal;

    public Diario toModel(){
        return Diario
                .builder()
                .dataDiario(this.dataDiario)
                .animal(this.animal.toModel())
                .observacao(this.observacao)
                .build();
    }
}
