package com.sisnat.api.app.nomePopular;

import com.sisnat.api.app.nomePopular.dto.NomePopularConverterDTO;
import com.sisnat.api.app.nomePopular.dto.NomePopularDTO;
import com.sisnat.api.app.nomePopular.dto.NomePopularFindDTO;
import com.sisnat.api.app.nomePopular.dto.NomePopularInputDTO;
import com.sisnat.domain.nomePopular.NomePopular;
import com.sisnat.domain.nomePopular.service.NomePopularService;
import com.sisnat.util.pagination.PaginationRequest;
import com.sisnat.util.pagination.ResponseList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class NomePopularApp {

    private final NomePopularConverterDTO conversor;
    private final NomePopularService service;

    @Transactional(readOnly = true)
    public NomePopularDTO buscarPorID(final Long codigo) {
        log.debug("c=NomePopularApp, m=buscarPorID, codigo={}", codigo);

        final NomePopular resultado = service.buscarPorId(codigo);

        return conversor.converter(resultado);
    }

    @Transactional
    public NomePopularDTO deletarPorID(final Long id) {
        log.debug("c=NomePopularApp, m=deletarPorID, id={}", id);

        final NomePopular resultado = service.deletarPorID(id);

        return conversor.converter(resultado);
    }

    @Transactional
    public NomePopularDTO salvarNomePopular(final NomePopularInputDTO dto) {
        log.debug("c=NomePopularApp, m=salvarNomePopular, dto={}", dto);

        final NomePopular resultado = service.salvarNomePopular(dto.toModel());

        return conversor.converter(resultado);
    }

    @Transactional
    public NomePopularDTO atualizarNomePopular(final Long id, final NomePopularInputDTO dto) {
        log.debug("c=NomePopularApp, m=atualizarNomePopular, id={}, dto={}", id, dto);

        final NomePopular resultado = service.atualizarNomePopular(id, dto.toModel());

        return conversor.converter(resultado);
    }

    @Transactional(readOnly = true)
    public ResponseList<NomePopularDTO> buscarTodosNomePopular(final NomePopularFindDTO dto) {
        log.debug("c=NomePopularApp, m=buscarTodos, dto={}", dto);

        final var request = new PaginationRequest<>(dto.generateWhere(), dto.generatePagination());
        final var response = service.buscarTodosNomePopular(request);
        final var data = converterDTO(response.getData(), dto);

        return new ResponseList<>(data, response);
    }

    private List<NomePopularDTO> converterDTO(final List<NomePopular> NomePopulars, final NomePopularFindDTO dto) {
        final var fields = nonNull(dto.getFields()) ? dto.getFields() : new ArrayList<String>();

        final var dtos = NomePopulars.stream().map(p -> {
            final var builder = NomePopular.builder();

            builder.id(p.getId());

            if (fields.isEmpty() || fields.contains("nomePopular")) {
                builder.nomePopular(p.getNomePopular());
            }

            if (fields.isEmpty() || fields.contains("nomeCientifico")) {
                builder.nomeCientifico(p.getNomeCientifico());
            }

            return new NomePopularDTO(builder.build());

        }).collect(Collectors.toList());

        return dtos;
    }
}
