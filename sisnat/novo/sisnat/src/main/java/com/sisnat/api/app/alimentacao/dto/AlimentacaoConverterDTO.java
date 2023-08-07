package com.sisnat.api.app.alimentacao.dto;

import com.sisnat.domain.alimentacao.Alimentacao;
import com.sisnat.util.message.MessageStack;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AlimentacaoConverterDTO {

    private final MessageStack messageStack;

    public AlimentacaoDTO converter(final Alimentacao alimentacao){
        log.debug("c=AlimentacaoConverterDTO, m=converter, alimentacao={}", alimentacao);

        final var dto = new AlimentacaoDTO();

        dto.setId(alimentacao.getId());
        dto.setDataAlimentacao(alimentacao.getDataAlimentacao());
        dto.setAlimento(alimentacao.getAlimento());
        dto.setAnimal(alimentacao.getAnimal());

        dto.setMensagem(messageStack.getMensagem());

        return dto;
    }

}
