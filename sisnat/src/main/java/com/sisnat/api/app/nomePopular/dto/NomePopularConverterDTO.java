package com.sisnat.api.app.nomePopular.dto;

import com.sisnat.domain.nomePopular.NomePopular;
import com.sisnat.util.message.MessageStack;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NomePopularConverterDTO {

    private final MessageStack messageStack;

    public NomePopularDTO converter(final NomePopular nomePopular){
        log.debug("c=NomePopularConverterDTO, m=converter, nomePopular={}", nomePopular);

        final var dto = new NomePopularDTO();

        dto.setId(nomePopular.getId());
        dto.setNomePopular(nomePopular.getNomePopular());
        dto.setNomeCientifico(nomePopular.getNomeCientifico());

        dto.setMensagem(messageStack.getMensagem());

        return dto;
    }
}
