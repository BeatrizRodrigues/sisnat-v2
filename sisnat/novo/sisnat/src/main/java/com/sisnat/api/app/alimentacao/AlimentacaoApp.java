package com.sisnat.api.app.alimentacao;

import com.sisnat.api.app.alimentacao.dto.AlimentacaoConverterDTO;
import com.sisnat.api.app.alimentacao.dto.AlimentacaoDTO;
import com.sisnat.api.app.alimentacao.dto.AlimentacaoFindDTO;
import com.sisnat.api.app.alimentacao.dto.AlimentacaoInputDTO;
import com.sisnat.domain.alimentacao.Alimentacao;
import com.sisnat.domain.alimentacao.service.AlimentacaoService;
import com.sisnat.domain.animal.service.AnimalService;
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
public class AlimentacaoApp {

    private final AlimentacaoConverterDTO conversor;
    private final AlimentacaoService service;
    private final AnimalService animalService;

    @Transactional(readOnly = true)
    public AlimentacaoDTO buscarPorID(final Long codigo) {
        log.debug("c=AlimentacaoApp, m=buscarPorID, codigo={}", codigo);

        final Alimentacao resultado = service.buscarPorId(codigo);

        return conversor.converter(resultado);
    }

    @Transactional
    public AlimentacaoDTO deletarPorID(final Long id) {
        log.debug("c=AlimentacaoApp, m=deletarPorID, id={}", id);

        final Alimentacao resultado = service.deletarPorID(id);

        return conversor.converter(resultado);
    }

    @Transactional
    public AlimentacaoDTO salvarAlimentacao(final AlimentacaoInputDTO dto) {
        log.debug("c=AlimentacaoApp, m=salvarAlimentacao, dto={}", dto);

        final Alimentacao resultado = service.salvarAlimentacao(dto.toModel());

        return conversor.converter(resultado);
    }

    @Transactional
    public AlimentacaoDTO atualizarAlimentacao(final Long id, final AlimentacaoInputDTO dto) {
        log.debug("c=AlimentacaoApp, m=atualizarAlimentacao, id={}, dto={}", id, dto);

        final Alimentacao resultado = service.atualizarAlimentacao(id, dto.toModel());

        return conversor.converter(resultado);
    }

    @Transactional(readOnly = true)
    public ResponseList<AlimentacaoDTO> buscarTodosAlimentacao(final AlimentacaoFindDTO dto) {
        log.debug("c=AlimentacaoApp, m=buscarTodos, dto={}", dto);

        final var request = new PaginationRequest<>(dto.generateWhere(), dto.generatePagination());
        final var response = service.buscarTodosAlimentacao(request);
        final var data = converterDTO(response.getData(), dto);

        return new ResponseList<>(data, response);
    }

    private List<AlimentacaoDTO> converterDTO(final List<Alimentacao> alimentacao, final AlimentacaoFindDTO dto) {
        final var fields = nonNull(dto.getFields()) ? dto.getFields() : new ArrayList<String>();

        final var dtos = alimentacao.stream().map(p -> {
            final var builder = Alimentacao.builder();

            builder.id(p.getId());

            if (fields.isEmpty() || fields.contains("dataAlimentacao")) {
                builder.dataAlimentacao(p.getDataAlimentacao());
            }

            if (fields.isEmpty() || fields.contains("quantidade")) {
                builder.quantidade(p.getQuantidade());
            }

            if (fields.isEmpty() || fields.contains("alimento")) {
                builder.alimento(p.getAlimento());
            }

            if (fields.isEmpty() || fields.contains("animal")) {
                builder.animal(p.getAnimal());
            }

            return new AlimentacaoDTO(builder.build());

        }).collect(Collectors.toList());

        return dtos;
    }
}
