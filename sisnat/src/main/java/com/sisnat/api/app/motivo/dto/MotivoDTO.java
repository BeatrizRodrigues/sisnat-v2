package com.sisnat.api.app.motivo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sisnat.domain.motivo.Motivo;
import com.sisnat.util.message.Message;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MotivoDTO {

    private Long id;
    private String motivo;

    private Message mensagem;

    public MotivoDTO(final Motivo motivo){
        this.id = motivo.getId();
        this.motivo = motivo.getMotivo();
    }

}
