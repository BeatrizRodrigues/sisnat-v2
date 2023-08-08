package com.sisnat.api.app.morfometriaArachnida;

import com.sisnat.api.app.morfometriaArachnida.dto.MorfArachnidaConverterDTO;
import com.sisnat.api.app.morfometriaArachnida.dto.MorfArachnidaDTO;
import com.sisnat.api.app.morfometriaArachnida.dto.MorfArachnidaFindDTO;
import com.sisnat.api.app.morfometriaArachnida.dto.MorfArachnidaInputDTO;
import com.sisnat.domain.ClassesAnimais;
import com.sisnat.domain.animal.service.AnimalService;
import com.sisnat.domain.morfometriaArachnida.MorfometriaArachnida;
import com.sisnat.domain.morfometriaArachnida.service.MorfometriaArachnidaService;
import com.sisnat.util.exception.NexusNotFoundException;
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
public class MorfometriaArachnidaApp {

    private final MorfArachnidaConverterDTO conversor;
    private final MorfometriaArachnidaService service;
    private final AnimalService animalService;

    @Transactional(readOnly = true)
    public MorfArachnidaDTO buscarPorID(final Long codigo) {
        log.debug("c=MorfometriaArachnidaApp, m=buscarPorID, codigo={}", codigo);

        final MorfometriaArachnida resultado = service.buscarPorId(codigo);

        return conversor.converter(resultado);
    }

    @Transactional
    public MorfArachnidaDTO deletarPorID(final Long id) {
        log.debug("c=MorfometriaArachnidaApp, m=deletarPorID, id={}", id);

        final MorfometriaArachnida resultado = service.deletarPorID(id);

        return conversor.converter(resultado);
    }

    @Transactional
    public MorfArachnidaDTO salvarMorfometriaArachnida(final MorfArachnidaInputDTO dto) {
        log.debug("c=MorfometriaArachnidaApp, m=salvarMorfometriaArachnida, dto={}", dto);

        final var buscaAnimal = animalService.buscarPorId(dto.getAnimal().getCodigo());
        MorfometriaArachnida resultado = new MorfometriaArachnida();

        if (buscaAnimal.getClasse().equals(ClassesAnimais.ARACHNIDA)){
            resultado = service.salvarMorfometriaArachnida(dto.toModel());
        } else {
            log.error("Falha ao salvar Morfometria, Animal não pertence à classe Arachnida.", buscaAnimal);
            NexusNotFoundException.of("O Animal não pertence à classe Arachnida.");
        }

        return conversor.converter(resultado);
    }

    @Transactional
    public MorfArachnidaDTO atualizarMorfometriaArachnida(final Long id, final MorfArachnidaInputDTO dto) {
        log.debug("c=MorfometriaArachnidaApp, m=atualizarMorfometriaArachnida, id={}, dto={}", id, dto);

        final MorfometriaArachnida resultado = service.atualizarMorfometriaArachnida(id, dto.toModel());

        return conversor.converter(resultado);
    }

    @Transactional(readOnly = true)
    public ResponseList<MorfArachnidaDTO> buscarTodosMorfometriaArachnida(final MorfArachnidaFindDTO dto) {
        log.debug("c=MorfometriaArachnidaApp, m=buscarTodos, dto={}", dto);

        final var request = new PaginationRequest<>(dto.generateWhere(), dto.generatePagination());
        final var response = service.buscarTodosMorfometriaArachnida(request);
        final var data = converterDTO(response.getData(), dto);

        return new ResponseList<>(data, response);
    }

    private List<MorfArachnidaDTO> converterDTO(final List<MorfometriaArachnida> morfometriaArachnida, final MorfArachnidaFindDTO dto) {
        final var fields = nonNull(dto.getFields()) ? dto.getFields() : new ArrayList<String>();

        final var dtos = morfometriaArachnida.stream().map(p -> {
            final var builder = MorfometriaArachnida.builder();

            builder.id(p.getId());

            if (fields.isEmpty() || fields.contains("dataMorf")) {
                builder.dataMorf(p.getDataMorf());
            }

            if (fields.isEmpty() || fields.contains("peso")) {
                builder.peso(p.getPeso());
            }

            if (fields.isEmpty() || fields.contains("cc")) {
                builder.cc(p.getCc());
            }

            if (fields.isEmpty() || fields.contains("cp")) {
                builder.cp(p.getCp());
            }

            if (fields.isEmpty() || fields.contains("cpp")) {
                builder.cpp(p.getCpp());
            }

            if (fields.isEmpty() || fields.contains("animal")) {
                builder.animal(p.getAnimal());
            }

            return new MorfArachnidaDTO(builder.build());

        }).collect(Collectors.toList());

        return dtos;
    }
}
