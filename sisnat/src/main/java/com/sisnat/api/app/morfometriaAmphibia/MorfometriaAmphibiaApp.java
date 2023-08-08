package com.sisnat.api.app.morfometriaAmphibia;

import com.sisnat.api.app.morfometriaAmphibia.dto.MorfAmphibiaConverterDTO;
import com.sisnat.api.app.morfometriaAmphibia.dto.MorfAmphibiaDTO;
import com.sisnat.api.app.morfometriaAmphibia.dto.MorfAmphibiaFindDTO;
import com.sisnat.api.app.morfometriaAmphibia.dto.MorfAmphibiaInputDTO;
import com.sisnat.domain.ClassesAnimais;
import com.sisnat.domain.animal.service.AnimalService;
import com.sisnat.domain.morfometriaAmphibia.MorfometriaAmphibia;
import com.sisnat.domain.morfometriaAmphibia.service.MorfometriaAmphibiaService;
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
public class MorfometriaAmphibiaApp {

    private final MorfAmphibiaConverterDTO conversor;
    private final MorfometriaAmphibiaService service;
    private final AnimalService animalService;

    @Transactional(readOnly = true)
    public MorfAmphibiaDTO buscarPorID(final Long codigo) {
        log.debug("c=MorfometriaAmphibiaApp, m=buscarPorID, codigo={}", codigo);

        final MorfometriaAmphibia resultado = service.buscarPorId(codigo);

        return conversor.converter(resultado);
    }

    @Transactional
    public MorfAmphibiaDTO deletarPorID(final Long id) {
        log.debug("c=MorfometriaAmphibiaApp, m=deletarPorID, id={}", id);

        final MorfometriaAmphibia resultado = service.deletarPorID(id);

        return conversor.converter(resultado);
    }

    @Transactional
    public MorfAmphibiaDTO salvarMorfometriaAmphibia(final MorfAmphibiaInputDTO dto) {
        log.debug("c=MorfometriaAmphibiaApp, m=salvarMorfometriaAmphibia, dto={}", dto);

        final var buscaAnimal = animalService.buscarPorId(dto.getAnimal().getCodigo());
        MorfometriaAmphibia resultado = new MorfometriaAmphibia();

        if (buscaAnimal.getClasse().equals(ClassesAnimais.AMPHIBIA)){
            resultado = service.salvarMorfometriaAmphibia(dto.toModel());
        } else {
            log.error("Falha ao salvar Morfometria, Animal não pertence à classe Amphibia.", buscaAnimal);
            NexusNotFoundException.of("O Animal não pertence à classe Amphibia.");
        }

        return conversor.converter(resultado);
    }

    @Transactional
    public MorfAmphibiaDTO atualizarMorfometriaAmphibia(final Long id, final MorfAmphibiaInputDTO dto) {
        log.debug("c=MorfometriaAmphibiaApp, m=atualizarMorfometriaAmphibia, id={}, dto={}", id, dto);

        final MorfometriaAmphibia resultado = service.atualizarMorfometriaAmphibia(id, dto.toModel());

        return conversor.converter(resultado);
    }

    @Transactional(readOnly = true)
    public ResponseList<MorfAmphibiaDTO> buscarTodosMorfometriaAmphibia(final MorfAmphibiaFindDTO dto) {
        log.debug("c=MorfometriaAmphibiaApp, m=buscarTodos, dto={}", dto);

        final var request = new PaginationRequest<>(dto.generateWhere(), dto.generatePagination());
        final var response = service.buscarTodosMorfometriaAmphibia(request);
        final var data = converterDTO(response.getData(), dto);

        return new ResponseList<>(data, response);
    }

    private List<MorfAmphibiaDTO> converterDTO(final List<MorfometriaAmphibia> morfometriaAmphibia, final MorfAmphibiaFindDTO dto) {
        final var fields = nonNull(dto.getFields()) ? dto.getFields() : new ArrayList<String>();

        final var dtos = morfometriaAmphibia.stream().map(p -> {
            final var builder = MorfometriaAmphibia.builder();

            builder.id(p.getId());

            if (fields.isEmpty() || fields.contains("dataMedicao")) {
                builder.dataMedicao(p.getDataMedicao());
            }

            if (fields.isEmpty() || fields.contains("peso")) {
                builder.peso(p.getPeso());
            }

            if (fields.isEmpty() || fields.contains("crc")) {
                builder.crc(p.getCrc());
            }

            if (fields.isEmpty() || fields.contains("cc")) {
                builder.cc(p.getCc());
            }

            if (fields.isEmpty() || fields.contains("don")) {
                builder.don(p.getDon());
            }

            if (fields.isEmpty() || fields.contains("cf")) {
                builder.cf(p.getCf());
            }

            if (fields.isEmpty() || fields.contains("ct")) {
                builder.ct(p.getCt());
            }

            if (fields.isEmpty() || fields.contains("cta")) {
                builder.cta(p.getCta());
            }

            if (fields.isEmpty() || fields.contains("cp")) {
                builder.cp(p.getCp());
            }

            if (fields.isEmpty() || fields.contains("animal")) {
                builder.animal(p.getAnimal());
            }

            return new MorfAmphibiaDTO(builder.build());

        }).collect(Collectors.toList());

        return dtos;
    }
}
