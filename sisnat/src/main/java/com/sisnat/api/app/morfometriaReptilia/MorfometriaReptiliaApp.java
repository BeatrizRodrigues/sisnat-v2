package com.sisnat.api.app.morfometriaReptilia;

import com.sisnat.api.app.morfometriaReptilia.dto.MorfReptiliaConverterDTO;
import com.sisnat.api.app.morfometriaReptilia.dto.MorfReptiliaDTO;
import com.sisnat.api.app.morfometriaReptilia.dto.MorfReptiliaFindDTO;
import com.sisnat.api.app.morfometriaReptilia.dto.MorfReptiliaInputDTO;
import com.sisnat.domain.ClassesAnimais;
import com.sisnat.domain.animal.service.AnimalService;
import com.sisnat.domain.morfometriaReptilia.MorfometriaReptilia;
import com.sisnat.domain.morfometriaReptilia.service.MorfometriaReptiliaService;
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
public class MorfometriaReptiliaApp {

    private final MorfReptiliaConverterDTO conversor;
    private final MorfometriaReptiliaService service;
    private final AnimalService animalService;

    @Transactional(readOnly = true)
    public MorfReptiliaDTO buscarPorID(final Long codigo) {
        log.debug("c=MorfometriaReptiliaApp, m=buscarPorID, codigo={}", codigo);

        final MorfometriaReptilia resultado = service.buscarPorId(codigo);

        return conversor.converter(resultado);
    }

    @Transactional
    public MorfReptiliaDTO deletarPorID(final Long id) {
        log.debug("c=MorfometriaReptiliaApp, m=deletarPorID, id={}", id);

        final MorfometriaReptilia resultado = service.deletarPorID(id);

        return conversor.converter(resultado);
    }

    @Transactional
    public MorfReptiliaDTO salvarMorfometriaReptilia(final MorfReptiliaInputDTO dto) {
        log.debug("c=MorfometriaReptiliaApp, m=salvarMorfometriaReptilia, dto={}", dto);

        final var buscaAnimal = animalService.buscarPorId(dto.getAnimal().getCodigo());
        MorfometriaReptilia resultado = new MorfometriaReptilia();

        if (buscaAnimal.getClasse().equals(ClassesAnimais.REPTILIA)){
            resultado = service.salvarMorfometriaReptilia(dto.toModel());
        } else {
            log.error("Falha ao salvar Morfometria, Animal não pertence à classe Reptilia.", buscaAnimal);
            NexusNotFoundException.of("O Animal não pertence à classe Reptilia.");
        }

        return conversor.converter(resultado);
    }

    @Transactional
    public MorfReptiliaDTO atualizarMorfometriaReptilia(final Long id, final MorfReptiliaInputDTO dto) {
        log.debug("c=MorfometriaReptiliaApp, m=atualizarMorfometriaReptilia, id={}, dto={}", id, dto);

        final MorfometriaReptilia resultado = service.atualizarMorfometriaReptilia(id, dto.toModel());

        return conversor.converter(resultado);
    }

    @Transactional(readOnly = true)
    public ResponseList<MorfReptiliaDTO> buscarTodosMorfometriaReptilia(final MorfReptiliaFindDTO dto) {
        log.debug("c=MorfometriaReptiliaApp, m=buscarTodos, dto={}", dto);

        final var request = new PaginationRequest<>(dto.generateWhere(), dto.generatePagination());
        final var response = service.buscarTodosMorfometriaReptilia(request);
        final var data = converterDTO(response.getData(), dto);

        return new ResponseList<>(data, response);
    }

    private List<MorfReptiliaDTO> converterDTO(final List<MorfometriaReptilia> morfometriaReptilia, final MorfReptiliaFindDTO dto) {
        final var fields = nonNull(dto.getFields()) ? dto.getFields() : new ArrayList<String>();

        final var dtos = morfometriaReptilia.stream().map(p -> {
            final var builder = MorfometriaReptilia.builder();

            builder.id(p.getId());

            if (fields.isEmpty() || fields.contains("dataMorf")) {
                builder.dataMorf(p.getDataMorf());
            }

            if (fields.isEmpty() || fields.contains("peso")) {
                builder.peso(p.getPeso());
            }

            if (fields.isEmpty() || fields.contains("crc")) {
                builder.crc(p.getCrc());
            }

            if (fields.isEmpty() || fields.contains("animal")) {
                builder.animal(p.getAnimal());
            }

            return new MorfReptiliaDTO(builder.build());

        }).collect(Collectors.toList());

        return dtos;
    }
}
