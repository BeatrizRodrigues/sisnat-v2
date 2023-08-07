package com.sisnat.api.app.procedencia.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sisnat.domain.procedencia.Procedencia;
import com.sisnat.util.message.Message;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProcedenciaDTO {

    private Long id;
    private String procedencia;

    private Message mensagem;

    public ProcedenciaDTO(final Procedencia procedencia){
        this.id = procedencia.getId();
        this.procedencia = procedencia.getProcedencia();
    }

}
