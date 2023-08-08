package com.sisnat.api.app.morfometriaAve.dto;

import com.sisnat.domain.morfometriaAves.MorfometriaAves;
import com.sisnat.util.message.MessageStack;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MorfAveConverterDTO {

    private final MessageStack messageStack;

    public MorfAveDTO converter(final MorfometriaAves morfometriaAves){
        log.debug("c=MorfAveConverterDTO, m=converter, morfometriaAves={}", morfometriaAves);

        final var dto = new MorfAveDTO();

        dto.setId(morfometriaAves.getId());
        dto.setDataMorf(morfometriaAves.getDataMorf());
        dto.setPeso(morfometriaAves.getPeso());
        dto.setCb(morfometriaAves.getCb());
        dto.setCc(morfometriaAves.getCc());
        dto.setH(morfometriaAves.getH());
        dto.setCa(morfometriaAves.getCa());
        dto.setCt(morfometriaAves.getCt());
        dto.setObservacao(morfometriaAves.getObservacao());
        dto.setAnimal(morfometriaAves.getAnimal());

        dto.setMensagem(messageStack.getMensagem());

        return dto;
    }
}
