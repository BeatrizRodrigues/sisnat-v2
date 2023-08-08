package com.sisnat.api.app.ecdise;

import com.sisnat.api.app.ecdise.dto.EcdiseConverterDTO;
import com.sisnat.api.app.ecdise.dto.EcdiseDTO;
import com.sisnat.api.app.ecdise.dto.EcdiseFindDTO;
import com.sisnat.api.app.ecdise.dto.EcdiseInputDTO;
import com.sisnat.domain.ClassesAnimais;
import com.sisnat.domain.animal.service.AnimalService;
import com.sisnat.domain.ecdise.Ecdise;
import com.sisnat.domain.ecdise.service.EcdiseService;
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
public class EcdiseApp {

    private final EcdiseConverterDTO conversor;
    private final EcdiseService service;
    private final AnimalService animalService;

    @Transactional(readOnly = true)
    public EcdiseDTO buscarPorID(final Long codigo) {
        log.debug("c=EcdiseApp, m=buscarPorID, codigo={}", codigo);

        final Ecdise resultado = service.buscarPorId(codigo);

        return conversor.converter(resultado);
    }

    @Transactional
    public EcdiseDTO deletarPorID(final Long id) {
        log.debug("c=EcdiseApp, m=deletarPorID, id={}", id);

        final Ecdise resultado = service.deletarPorID(id);

        return conversor.converter(resultado);
    }

    @Transactional
    public EcdiseDTO salvarEcdise(final EcdiseInputDTO dto) {
        log.debug("c=EcdiseApp, m=salvarEcdise, dto={}", dto);

        final var buscaAnimal = animalService.buscarPorId(dto.getAnimal().getCodigo());
        Ecdise resultado = new Ecdise();

        if (buscaAnimal.getClasse().equals(ClassesAnimais.AMPHIBIA) ||
                buscaAnimal.getClasse().equals(ClassesAnimais.ARACHNIDA) ||
                buscaAnimal.getClasse().equals(ClassesAnimais.REPTILIA)){
            resultado = service.salvarEcdise(dto.toModel());
        } else {
            log.error("Falha ao salvar Ecdise, Animal não pertence à classe Amphibia.", buscaAnimal);
            NexusNotFoundException.of("O Animal não pertence à classe Amphibia.");
        }

        return conversor.converter(resultado);
    }

    @Transactional
    public EcdiseDTO atualizarEcdise(final Long id, final EcdiseInputDTO dto) {
        log.debug("c=EcdiseApp, m=atualizarEcdise, id={}, dto={}", id, dto);

        final Ecdise resultado = service.atualizarEcdise(id, dto.toModel());

        return conversor.converter(resultado);
    }

    @Transactional(readOnly = true)
    public ResponseList<EcdiseDTO> buscarTodosEcdise(final EcdiseFindDTO dto) {
        log.debug("c=EcdiseApp, m=buscarTodos, dto={}", dto);

        final var request = new PaginationRequest<>(dto.generateWhere(), dto.generatePagination());
        final var response = service.buscarTodosEcdise(request);
        final var data = converterDTO(response.getData(), dto);

        return new ResponseList<>(data, response);
    }

    private List<EcdiseDTO> converterDTO(final List<Ecdise> ecdise, final EcdiseFindDTO dto) {
        final var fields = nonNull(dto.getFields()) ? dto.getFields() : new ArrayList<String>();

        final var dtos = ecdise.stream().map(p -> {
            final var builder = Ecdise.builder();

            builder.id(p.getId());

            if (fields.isEmpty() || fields.contains("dataEcdise")) {
                builder.dataEcdise(p.getDataEcdise());
            }

            if (fields.isEmpty() || fields.contains("ecdise")) {
                builder.ecdise(p.getEcdise());
            }

            if (fields.isEmpty() || fields.contains("animal")) {
                builder.animal(p.getAnimal());
            }

            return new EcdiseDTO(builder.build());

        }).collect(Collectors.toList());

        return dtos;
    }
}
