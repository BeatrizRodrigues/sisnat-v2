package com.sisnat.api.app.morfometriaMammalia.dto;

import com.sisnat.domain.morfometriaMammalia.MorfometriaMammalia;
import com.sisnat.util.message.MessageStack;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MorfMammaliaConverterDTO {

    private final MessageStack messageStack;

    public MorfMammaliaDTO converter(final MorfometriaMammalia morfometriaMammalia){
        log.debug("c=MorfMammaliaConverterDTO, m=converter, morfometriaMammalia={}", morfometriaMammalia);

        final var dto = new MorfMammaliaDTO();

        dto.setId(morfometriaMammalia.getId());
        dto.setDataMorf(morfometriaMammalia.getDataMorf());
        dto.setPeso(morfometriaMammalia.getPeso());
        dto.setCra(morfometriaMammalia.getCra());
        dto.setCc(morfometriaMammalia.getCc());
        dto.setCm(morfometriaMammalia.getCm());
        dto.setCp(morfometriaMammalia.getCp());
        dto.setHo(morfometriaMammalia.getHo());
        dto.setObservacao(morfometriaMammalia.getObservacao());
        dto.setAnimal(morfometriaMammalia.getAnimal());

        dto.setMensagem(messageStack.getMensagem());

        return dto;
    }
}
