package com.sisnat.api.app.atendimento.dto;

import com.sisnat.domain.atendimento.Atendimento;
import com.sisnat.util.message.MessageStack;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AtendimentoConverterDTO {

    private final MessageStack messageStack;

    public AtendimentoDTO converter(final Atendimento atendimento){
        log.debug("c=AtendimentoConverterDTO, m=converter, atendimento={}", atendimento);

        final var dto = new AtendimentoDTO();

        dto.setId(atendimento.getId());
        dto.setDataProcedimento(atendimento.getDataProcedimento());
        dto.setProcedimento(atendimento.getProcedimento());
        dto.setAnimal(atendimento.getAnimal());

        dto.setMensagem(messageStack.getMensagem());

        return dto;
    }
}
