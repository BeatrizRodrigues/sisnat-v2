package com.sisnat.api.app.morfometriaArachnida.dto;

import com.sisnat.domain.morfometriaArachnida.MorfometriaArachnida;
import com.sisnat.util.message.MessageStack;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MorfArachnidaConverterDTO {

    private final MessageStack messageStack;

    public MorfArachnidaDTO converter(final MorfometriaArachnida morfometriaArachnida){
        log.debug("c=MorfArachnidaConverterDTO, m=converter, morfometriaArachnida={}", morfometriaArachnida);

        final var dto = new MorfArachnidaDTO();

        dto.setId(morfometriaArachnida.getId());
        dto.setDataMorf(morfometriaArachnida.getDataMorf());
        dto.setPeso(morfometriaArachnida.getPeso());
        dto.setCc(morfometriaArachnida.getCc());
        dto.setCp(morfometriaArachnida.getCp());
        dto.setCpp(morfometriaArachnida.getCpp());
        dto.setObservacao(morfometriaArachnida.getObservacao());
        dto.setAnimal(morfometriaArachnida.getAnimal());

        dto.setMensagem(messageStack.getMensagem());

        return dto;
    }
}
