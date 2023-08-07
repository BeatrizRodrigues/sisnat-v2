package com.sisnat.api.app.atendimento.dto;

import com.sisnat.api.app.animal.dto.AnimalAtribuirDTO;
import com.sisnat.domain.atendimento.Atendimento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AtendimentoInputDTO {

    private Date dataProcedimento;
    private String procedimento;
    private AnimalAtribuirDTO animal;

    public Atendimento toModel(){
        return Atendimento
                .builder()
                .dataProcedimento(this.dataProcedimento)
                .animal(this.animal.toModel())
                .procedimento(this.procedimento)
                .build();
    }
}
