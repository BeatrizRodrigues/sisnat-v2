package com.sisnat.api.app.atendimento;

import com.sisnat.api.app.atendimento.dto.AtendimentoConverterDTO;
import com.sisnat.api.app.atendimento.dto.AtendimentoDTO;
import com.sisnat.api.app.atendimento.dto.AtendimentoFindDTO;
import com.sisnat.api.app.atendimento.dto.AtendimentoInputDTO;
import com.sisnat.domain.atendimento.Atendimento;
import com.sisnat.domain.atendimento.service.AtendimentoService;
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
public class AtendimentoApp {

    private final AtendimentoConverterDTO conversor;
    private final AtendimentoService service;

    @Transactional(readOnly = true)
    public AtendimentoDTO buscarPorID(final Long codigo) {
        log.debug("c=AtendimentoApp, m=buscarPorID, codigo={}", codigo);

        final Atendimento resultado = service.buscarPorId(codigo);

        return conversor.converter(resultado);
    }

    @Transactional
    public AtendimentoDTO deletarPorID(final Long id) {
        log.debug("c=AtendimentoApp, m=deletarPorID, id={}", id);

        final Atendimento resultado = service.deletarPorID(id);

        return conversor.converter(resultado);
    }

    @Transactional
    public AtendimentoDTO salvarAtendimento(final AtendimentoInputDTO dto) {
        log.debug("c=AtendimentoApp, m=salvarAtendimento, dto={}", dto);

        final Atendimento resultado = service.salvarAtendimento(dto.toModel());

        return conversor.converter(resultado);
    }

    @Transactional
    public AtendimentoDTO atualizarAtendimento(final Long id, final AtendimentoInputDTO dto) {
        log.debug("c=AtendimentoApp, m=atualizarAtendimento, id={}, dto={}", id, dto);

        final Atendimento resultado = service.atualizarAtendimento(id, dto.toModel());

        return conversor.converter(resultado);
    }

    @Transactional(readOnly = true)
    public ResponseList<AtendimentoDTO> buscarTodosAtendimento(final AtendimentoFindDTO dto) {
        log.debug("c=AtendimentoApp, m=buscarTodos, dto={}", dto);

        final var request = new PaginationRequest<>(dto.generateWhere(), dto.generatePagination());
        final var response = service.buscarTodosAtendimento(request);
        final var data = converterDTO(response.getData(), dto);

        return new ResponseList<>(data, response);
    }

    private List<AtendimentoDTO> converterDTO(final List<Atendimento> atendimento, final AtendimentoFindDTO dto) {
        final var fields = nonNull(dto.getFields()) ? dto.getFields() : new ArrayList<String>();

        final var dtos = atendimento.stream().map(p -> {
            final var builder = Atendimento.builder();

            builder.id(p.getId());

            if (fields.isEmpty() || fields.contains("dataProcedimento")) {
                builder.dataProcedimento(p.getDataProcedimento());
            }

            if (fields.isEmpty() || fields.contains("procedimento")) {
                builder.procedimento(p.getProcedimento());
            }

            if (fields.isEmpty() || fields.contains("animal")) {
                builder.animal(p.getAnimal());
            }

            return new AtendimentoDTO(builder.build());

        }).collect(Collectors.toList());

        return dtos;
    }
}
