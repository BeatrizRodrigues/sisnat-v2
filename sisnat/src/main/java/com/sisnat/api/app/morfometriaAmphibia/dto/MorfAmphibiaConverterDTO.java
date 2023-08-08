package com.sisnat.api.app.morfometriaAmphibia.dto;

import com.sisnat.domain.morfometriaAmphibia.MorfometriaAmphibia;
import com.sisnat.util.message.MessageStack;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MorfAmphibiaConverterDTO {

    private final MessageStack messageStack;

    public MorfAmphibiaDTO converter(final MorfometriaAmphibia morfometriaAmphibia){
        log.debug("c=MorfAmphibiaConverterDTO, m=converter, morfometriaAmphibia={}", morfometriaAmphibia);

        final var dto = new MorfAmphibiaDTO();

        dto.setId(morfometriaAmphibia.getId());
        dto.setDataMedicao(morfometriaAmphibia.getDataMedicao());
        dto.setPeso(morfometriaAmphibia.getPeso());
        dto.setCrc(morfometriaAmphibia.getCrc());
        dto.setCc(morfometriaAmphibia.getCc());
        dto.setDon(morfometriaAmphibia.getDon());
        dto.setCf(morfometriaAmphibia.getCf());
        dto.setCt(morfometriaAmphibia.getCt());
        dto.setCta(morfometriaAmphibia.getCta());
        dto.setCp(morfometriaAmphibia.getCp());
        dto.setObservacao(morfometriaAmphibia.getObservacao());
        dto.setAnimal(morfometriaAmphibia.getAnimal());

        dto.setMensagem(messageStack.getMensagem());

        return dto;
    }
}
