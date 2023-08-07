package com.sisnat.api.app.atendimento.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sisnat.domain.animal.Animal;
import com.sisnat.domain.atendimento.Atendimento;
import com.sisnat.util.message.Message;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AtendimentoDTO {

    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dataProcedimento;

    private String procedimento;
    private Animal animal;

    private Message mensagem;

    public AtendimentoDTO(final Atendimento atendimento){
        this.id = atendimento.getId();
        this.dataProcedimento = atendimento.getDataProcedimento();
        this.procedimento = atendimento.getProcedimento();
        this.animal = atendimento.getAnimal();
    }

}
