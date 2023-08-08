package com.sisnat.api.app.morfometriaReptilia.dto;

import com.sisnat.domain.morfometriaReptilia.MorfometriaReptilia;
import com.sisnat.util.message.MessageStack;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MorfReptiliaConverterDTO {

    private final MessageStack messageStack;

    public MorfReptiliaDTO converter(final MorfometriaReptilia morfometriaReptilia){
        log.debug("c=MorfReptiliaConverterDTO, m=converter, morfometriaReptilia={}", morfometriaReptilia);

        final var dto = new MorfReptiliaDTO();

        dto.setId(morfometriaReptilia.getId());
        dto.setDataMorf(morfometriaReptilia.getDataMorf());
        dto.setPeso(morfometriaReptilia.getPeso());
        dto.setCrc(morfometriaReptilia.getCrc());
        dto.setObservacao(morfometriaReptilia.getObservacao());
        dto.setAnimal(morfometriaReptilia.getAnimal());

        dto.setMensagem(messageStack.getMensagem());

        return dto;
    }
}
