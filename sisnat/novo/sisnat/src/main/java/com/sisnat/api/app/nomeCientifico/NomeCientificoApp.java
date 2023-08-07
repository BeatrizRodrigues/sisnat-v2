package com.sisnat.api.app.nomeCientifico;

import com.sisnat.api.app.nomeCientifico.dto.NomeCientificoConverterDTO;
import com.sisnat.api.app.nomeCientifico.dto.NomeCientificoDTO;
import com.sisnat.api.app.nomeCientifico.dto.NomeCientificoFindDTO;
import com.sisnat.api.app.nomeCientifico.dto.NomeCientificoInputDTO;
import com.sisnat.domain.nomeCientifico.NomeCientifico;
import com.sisnat.domain.nomeCientifico.service.NomeCientificoService;
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
public class NomeCientificoApp {

    private final NomeCientificoConverterDTO conversor;
    private final NomeCientificoService service;

    @Transactional(readOnly = true)
    public NomeCientificoDTO buscarPorID(final Long codigo) {
        log.debug("c=NomeCientificoApp, m=buscarPorID, codigo={}", codigo);

        final NomeCientifico resultado = service.buscarPorId(codigo);

        return conversor.converter(resultado);
    }

    @Transactional
    public NomeCientificoDTO deletarPorID(final Long id) {
        log.debug("c=NomeCientificoApp, m=deletarPorID, id={}", id);

        final NomeCientifico resultado = service.deletarPorID(id);

        return conversor.converter(resultado);
    }

    @Transactional
    public NomeCientificoDTO salvarNomeCientifico(final NomeCientificoInputDTO dto) {
        log.debug("c=NomeCientificoApp, m=salvarNomeCientifico, dto={}", dto);

        final NomeCientifico resultado = service.salvarNomeCientifico(dto.toModel());

        return conversor.converter(resultado);
    }

    @Transactional
    public NomeCientificoDTO atualizarNomeCientifico(final Long id, final NomeCientificoInputDTO dto) {
        log.debug("c=NomeCientificoApp, m=atualizarNomeCientifico, id={}, dto={}", id, dto);

        final NomeCientifico resultado = service.atualizarNomeCientifico(id, dto.toModel());

        return conversor.converter(resultado);
    }

    @Transactional(readOnly = true)
    public ResponseList<NomeCientificoDTO> buscarTodosNomeCientifico(final NomeCientificoFindDTO dto) {
        log.debug("c=NomeCientificoApp, m=buscarTodos, dto={}", dto);

        final var request = new PaginationRequest<>(dto.generateWhere(), dto.generatePagination());
        final var response = service.buscarTodosNomeCientifico(request);
        final var data = converterDTO(response.getData(), dto);

        return new ResponseList<>(data, response);
    }

    private List<NomeCientificoDTO> converterDTO(final List<NomeCientifico> NomeCientificos, final NomeCientificoFindDTO dto) {
        final var fields = nonNull(dto.getFields()) ? dto.getFields() : new ArrayList<String>();

        final var dtos = NomeCientificos.stream().map(p -> {
            final var builder = NomeCientifico.builder();

            builder.id(p.getId());

            if (fields.isEmpty() || fields.contains("nomeCientifico")) {
                builder.nomeCientifico(p.getNomeCientifico());
            }

            return new NomeCientificoDTO(builder.build());

        }).collect(Collectors.toList());

        return dtos;
    }
}
