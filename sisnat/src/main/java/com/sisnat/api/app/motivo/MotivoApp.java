package com.sisnat.api.app.motivo;

import com.sisnat.api.app.motivo.dto.MotivoConverterDTO;
import com.sisnat.api.app.motivo.dto.MotivoDTO;
import com.sisnat.api.app.motivo.dto.MotivoFindDTO;
import com.sisnat.api.app.motivo.dto.MotivoInputDTO;
import com.sisnat.domain.motivo.Motivo;
import com.sisnat.domain.motivo.service.MotivoService;
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
public class MotivoApp {

    private final MotivoConverterDTO conversor;
    private final MotivoService service;

    @Transactional(readOnly = true)
    public MotivoDTO buscarPorID(final Long codigo) {
        log.debug("c=MotivoApp, m=buscarPorID, codigo={}", codigo);

        final Motivo resultado = service.buscarPorId(codigo);

        return conversor.converter(resultado);
    }

    @Transactional
    public MotivoDTO deletarPorID(final Long id) {
        log.debug("c=MotivoApp, m=deletarPorID, id={}", id);

        final Motivo resultado = service.deletarPorID(id);

        return conversor.converter(resultado);
    }

    @Transactional
    public MotivoDTO salvarMotivo(final MotivoInputDTO dto) {
        log.debug("c=MotivoApp, m=salvarMotivo, dto={}", dto);

        final Motivo resultado = service.salvarMotivo(dto.toModel());

        return conversor.converter(resultado);
    }

    @Transactional
    public MotivoDTO atualizarMotivo(final Long id, final MotivoInputDTO dto) {
        log.debug("c=MotivoApp, m=atualizarMotivo, id={}, dto={}", id, dto);

        final Motivo resultado = service.atualizarMotivo(id, dto.toModel());

        return conversor.converter(resultado);
    }

    @Transactional(readOnly = true)
    public ResponseList<MotivoDTO> buscarTodosMotivo(final MotivoFindDTO dto) {
        log.debug("c=MotivoApp, m=buscarTodos, dto={}", dto);

        final var request = new PaginationRequest<>(dto.generateWhere(), dto.generatePagination());
        final var response = service.buscarTodosMotivo(request);
        final var data = converterDTO(response.getData(), dto);

        return new ResponseList<>(data, response);
    }

    private List<MotivoDTO> converterDTO(final List<Motivo> motivos, final MotivoFindDTO dto) {
        final var fields = nonNull(dto.getFields()) ? dto.getFields() : new ArrayList<String>();

        final var dtos = motivos.stream().map(p -> {
            final var builder = Motivo.builder();

            builder.id(p.getId());

            if (fields.isEmpty() || fields.contains("motivo")) {
                builder.motivo(p.getMotivo());
            }

            return new MotivoDTO(builder.build());

        }).collect(Collectors.toList());

        return dtos;
    }
}
