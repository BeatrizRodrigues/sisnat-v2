package com.sisnat.api.app.procedencia;

import com.sisnat.api.app.procedencia.dto.ProcedenciaConverterDTO;
import com.sisnat.api.app.procedencia.dto.ProcedenciaDTO;
import com.sisnat.api.app.procedencia.dto.ProcedenciaFindDTO;
import com.sisnat.api.app.procedencia.dto.ProcedenciaInputDTO;
import com.sisnat.domain.procedencia.Procedencia;
import com.sisnat.domain.procedencia.service.ProcedenciaService;
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
public class ProcedenciaApp {

    private final ProcedenciaConverterDTO conversor;
    private final ProcedenciaService service;

    @Transactional(readOnly = true)
    public ProcedenciaDTO buscarPorID(final Long codigo) {
        log.debug("c=ProcedenciaApp, m=buscarPorID, codigo={}", codigo);

        final Procedencia resultado = service.buscarPorId(codigo);

        return conversor.converter(resultado);
    }

    @Transactional
    public ProcedenciaDTO deletarPorID(final Long id) {
        log.debug("c=ProcedenciaApp, m=deletarPorID, id={}", id);

        final Procedencia resultado = service.deletarPorID(id);

        return conversor.converter(resultado);
    }

    @Transactional
    public ProcedenciaDTO salvarProcedencia(final ProcedenciaInputDTO dto) {
        log.debug("c=ProcedenciaApp, m=salvarProcedencia, dto={}", dto);

        final Procedencia resultado = service.salvarProcedencia(dto.toModel());

        return conversor.converter(resultado);
    }

    @Transactional
    public ProcedenciaDTO atualizarProcedencia(final Long id, final ProcedenciaInputDTO dto) {
        log.debug("c=ProcedenciaApp, m=atualizarProcedencia, id={}, dto={}", id, dto);

        final Procedencia resultado = service.atualizarProcedencia(id, dto.toModel());

        return conversor.converter(resultado);
    }

    @Transactional(readOnly = true)
    public ResponseList<ProcedenciaDTO> buscarTodosProcedencia(final ProcedenciaFindDTO dto) {
        log.debug("c=ProcedenciaApp, m=buscarTodos, dto={}", dto);

        final var request = new PaginationRequest<>(dto.generateWhere(), dto.generatePagination());
        final var response = service.buscarTodosProcedencia(request);
        final var data = converterDTO(response.getData(), dto);

        return new ResponseList<>(data, response);
    }

    private List<ProcedenciaDTO> converterDTO(final List<Procedencia> procedencias, final ProcedenciaFindDTO dto) {
        final var fields = nonNull(dto.getFields()) ? dto.getFields() : new ArrayList<String>();

        final var dtos = procedencias.stream().map(p -> {
            final var builder = Procedencia.builder();

            builder.id(p.getId());

            if (fields.isEmpty() || fields.contains("procedencia")) {
                builder.procedencia(p.getProcedencia());
            }

            return new ProcedenciaDTO(builder.build());

        }).collect(Collectors.toList());

        return dtos;
    }
}
