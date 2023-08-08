package com.sisnat.api.app.diario.dto;

import com.sisnat.domain.diario.Diario;
import com.sisnat.util.message.MessageStack;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DiarioConverterDTO {

    private final MessageStack messageStack;

    public DiarioDTO converter(final Diario diario){
        log.debug("c=DiarioConverterDTO, m=converter, diario={}", diario);

        final var dto = new DiarioDTO();

        dto.setId(diario.getId());
        dto.setDataDiario(diario.getDataDiario());
        dto.setObservacao(diario.getObservacao());
        dto.setAnimal(diario.getAnimal());

        dto.setMensagem(messageStack.getMensagem());

        return dto;
    }
}
