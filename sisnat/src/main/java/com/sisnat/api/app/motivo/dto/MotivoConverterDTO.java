package com.sisnat.api.app.motivo.dto;

import com.sisnat.domain.motivo.Motivo;
import com.sisnat.util.message.MessageStack;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MotivoConverterDTO {

    private final MessageStack messageStack;

    public MotivoDTO converter(final Motivo motivo){
        log.debug("c=MotivoConverterDTO, m=converter, motivo={}", motivo);

        final var dto = new MotivoDTO();

        dto.setId(motivo.getId());
        dto.setMotivo(motivo.getMotivo());

        dto.setMensagem(messageStack.getMensagem());

        return dto;
    }
}
