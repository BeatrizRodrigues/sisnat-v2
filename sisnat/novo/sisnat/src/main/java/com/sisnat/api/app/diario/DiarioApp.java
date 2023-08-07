package com.sisnat.api.app.diario;

import com.sisnat.api.app.diario.dto.DiarioConverterDTO;
import com.sisnat.api.app.diario.dto.DiarioDTO;
import com.sisnat.api.app.diario.dto.DiarioFindDTO;
import com.sisnat.api.app.diario.dto.DiarioInputDTO;
import com.sisnat.domain.diario.Diario;
import com.sisnat.domain.diario.service.DiarioService;
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
public class DiarioApp {

    private final DiarioConverterDTO conversor;
    private final DiarioService service;

    @Transactional(readOnly = true)
    public DiarioDTO buscarPorID(final Long codigo) {
        log.debug("c=DiarioApp, m=buscarPorID, codigo={}", codigo);

        final Diario resultado = service.buscarPorId(codigo);

        return conversor.converter(resultado);
    }

    @Transactional
    public DiarioDTO deletarPorID(final Long id) {
        log.debug("c=DiarioApp, m=deletarPorID, id={}", id);

        final Diario resultado = service.deletarPorID(id);

        return conversor.converter(resultado);
    }

    @Transactional
    public DiarioDTO salvarDiario(final DiarioInputDTO dto) {
        log.debug("c=DiarioApp, m=salvarDiario, dto={}", dto);

        final Diario resultado = service.salvarDiario(dto.toModel());

        return conversor.converter(resultado);
    }

    @Transactional
    public DiarioDTO atualizarDiario(final Long id, final DiarioInputDTO dto) {
        log.debug("c=DiarioApp, m=atualizarDiario, id={}, dto={}", id, dto);

        final Diario resultado = service.atualizarDiario(id, dto.toModel());

        return conversor.converter(resultado);
    }

    @Transactional(readOnly = true)
    public ResponseList<DiarioDTO> buscarTodosDiario(final DiarioFindDTO dto) {
        log.debug("c=DiarioApp, m=buscarTodos, dto={}", dto);

        final var request = new PaginationRequest<>(dto.generateWhere(), dto.generatePagination());
        final var response = service.buscarTodosDiario(request);
        final var data = converterDTO(response.getData(), dto);

        return new ResponseList<>(data, response);
    }

    private List<DiarioDTO> converterDTO(final List<Diario> diario, final DiarioFindDTO dto) {
        final var fields = nonNull(dto.getFields()) ? dto.getFields() : new ArrayList<String>();

        final var dtos = diario.stream().map(p -> {
            final var builder = Diario.builder();

            builder.id(p.getId());

            if (fields.isEmpty() || fields.contains("dataDiario")) {
                builder.dataDiario(p.getDataDiario());
            }

            if (fields.isEmpty() || fields.contains("observacao")) {
                builder.observacao(p.getObservacao());
            }

            if (fields.isEmpty() || fields.contains("animal")) {
                builder.animal(p.getAnimal());
            }

            return new DiarioDTO(builder.build());

        }).collect(Collectors.toList());

        return dtos;
    }
}
