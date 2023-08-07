package com.sisnat.api.app.diario.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sisnat.domain.animal.Animal;
import com.sisnat.domain.diario.Diario;
import com.sisnat.util.message.Message;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiarioDTO {

    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dataDiario;

    private String observacao;
    private Animal animal;

    private Message mensagem;

    public DiarioDTO(final Diario diario){
        this.id = diario.getId();
        this.dataDiario = diario.getDataDiario();
        this.observacao = diario.getObservacao();
        this.animal = diario.getAnimal();
    }

}
