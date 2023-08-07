package com.sisnat.api.app.morfometriaAve;

import com.sisnat.api.app.morfometriaAve.dto.MorfAveConverterDTO;
import com.sisnat.api.app.morfometriaAve.dto.MorfAveDTO;
import com.sisnat.api.app.morfometriaAve.dto.MorfAveFindDTO;
import com.sisnat.api.app.morfometriaAve.dto.MorfAveInputDTO;
import com.sisnat.domain.ClassesAnimais;
import com.sisnat.domain.animal.service.AnimalService;
import com.sisnat.domain.morfometriaAves.MorfometriaAves;
import com.sisnat.domain.morfometriaAves.service.MorfometriaAvesService;
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
public class MorfometriaAveApp {

    private final MorfAveConverterDTO conversor;
    private final MorfometriaAvesService service;
    private final AnimalService animalService;

    @Transactional(readOnly = true)
    public MorfAveDTO buscarPorID(final Long codigo) {
        log.debug("c=MorfometriaAveApp, m=buscarPorID, codigo={}", codigo);

        final MorfometriaAves resultado = service.buscarPorId(codigo);

        return conversor.converter(resultado);
    }

    @Transactional
    public MorfAveDTO deletarPorID(final Long id) {
        log.debug("c=MorfometriaAveApp, m=deletarPorID, id={}", id);

        final MorfometriaAves resultado = service.deletarPorID(id);

        return conversor.converter(resultado);
    }

    @Transactional
    public MorfAveDTO salvarMorfometriaAve(final MorfAveInputDTO dto) {
        log.debug("c=MorfometriaAveApp, m=salvarMorfometriaAve, dto={}", dto);

        final var busca = animalService.buscarPorId(dto.getAnimal().getCodigo());
        MorfometriaAves resultado = new MorfometriaAves();

        if (busca.equals(ClassesAnimais.AVE)){
            resultado = service.salvarMorfometriaAves(dto.toModel());
        } else {
            log.error("Falha ao salvar Morfometria, Animal não pertence à classe Ave.", busca);
            NexusNotFoundException.of("O Animal não pertence à classe Ave.");
        }

        return conversor.converter(resultado);
    }

    @Transactional
    public MorfAveDTO atualizarMorfometriaAve(final Long id, final MorfAveInputDTO dto) {
        log.debug("c=MorfometriaAveApp, m=atualizarMorfometriaAve, id={}, dto={}", id, dto);

        final MorfometriaAves resultado = service.atualizarMorfometriaAves(id, dto.toModel());

        return conversor.converter(resultado);
    }

    @Transactional(readOnly = true)
    public ResponseList<MorfAveDTO> buscarTodosMorfometriaAves(final MorfAveFindDTO dto) {
        log.debug("c=MorfometriaAveApp, m=buscarTodos, dto={}", dto);

        final var request = new PaginationRequest<>(dto.generateWhere(), dto.generatePagination());
        final var response = service.buscarTodosMorfometriaAves(request);
        final var data = converterDTO(response.getData(), dto);

        return new ResponseList<>(data, response);
    }

    private List<MorfAveDTO> converterDTO(final List<MorfometriaAves> morfometriaAve, final MorfAveFindDTO dto) {
        final var fields = nonNull(dto.getFields()) ? dto.getFields() : new ArrayList<String>();

        final var dtos = morfometriaAve.stream().map(p -> {
            final var builder = MorfometriaAves.builder();

            builder.id(p.getId());

            if (fields.isEmpty() || fields.contains("dataMorf")) {
                builder.dataMorf(p.getDataMorf());
            }

            if (fields.isEmpty() || fields.contains("peso")) {
                builder.peso(p.getPeso());
            }

            if (fields.isEmpty() || fields.contains("cb")) {
                builder.cb(p.getCb());
            }

            if (fields.isEmpty() || fields.contains("cc")) {
                builder.cc(p.getCc());
            }

            if (fields.isEmpty() || fields.contains("h")) {
                builder.h(p.getH());
            }

            if (fields.isEmpty() || fields.contains("ca")) {
                builder.ca(p.getCa());
            }

            if (fields.isEmpty() || fields.contains("ct")) {
                builder.ct(p.getCt());
            }

            if (fields.isEmpty() || fields.contains("animal")) {
                builder.animal(p.getAnimal());
            }

            return new MorfAveDTO(builder.build());

        }).collect(Collectors.toList());

        return dtos;
    }
}
