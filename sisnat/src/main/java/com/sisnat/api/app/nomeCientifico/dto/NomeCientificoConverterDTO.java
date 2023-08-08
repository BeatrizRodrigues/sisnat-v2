package com.sisnat.api.app.nomeCientifico.dto;

import com.sisnat.domain.nomeCientifico.NomeCientifico;
import com.sisnat.util.message.MessageStack;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NomeCientificoConverterDTO {

    private final MessageStack messageStack;

    public NomeCientificoDTO converter(final NomeCientifico nomeCientifico){
        log.debug("c=MotivoConverterDTO, m=converter, nomeCientifico={}", nomeCientifico);

        final var dto = new NomeCientificoDTO();

        dto.setId(nomeCientifico.getId());
        dto.setNomeCientifico(nomeCientifico.getNomeCientifico());

        dto.setMensagem(messageStack.getMensagem());

        return dto;
    }
}