package com.sisnat.api.app.procedencia.dto;

import com.sisnat.domain.procedencia.Procedencia;
import com.sisnat.domain.statusAnimal.StatusAnimal;
import com.sisnat.util.message.MessageStack;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProcedenciaConverterDTO {

    private final MessageStack messageStack;

    public ProcedenciaDTO converter(final Procedencia procedencia){
        log.debug("c=ProcedenciaConverterDTO, m=converter, procedencia={}", procedencia);

        final var dto = new ProcedenciaDTO();

        dto.setId(procedencia.getId());
        dto.setProcedencia(procedencia.getProcedencia());

        dto.setMensagem(messageStack.getMensagem());

        return dto;
    }
}
