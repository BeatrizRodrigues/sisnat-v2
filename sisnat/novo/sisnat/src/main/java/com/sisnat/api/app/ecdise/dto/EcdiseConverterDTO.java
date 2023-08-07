package com.sisnat.api.app.ecdise.dto;

import com.sisnat.domain.ecdise.Ecdise;
import com.sisnat.util.message.MessageStack;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EcdiseConverterDTO {

    private final MessageStack messageStack;

    public EcdiseDTO converter(final Ecdise ecdise){
        log.debug("c=EcdiseConverterDTO, m=converter, ecdise={}", ecdise);

        final var dto = new EcdiseDTO();

        dto.setId(ecdise.getId());
        dto.setDataEcdise(ecdise.getDataEcdise());
        dto.setEcdise(ecdise.getEcdise());
        dto.setAnimal(ecdise.getAnimal());

        dto.setMensagem(messageStack.getMensagem());

        return dto;
    }

}
