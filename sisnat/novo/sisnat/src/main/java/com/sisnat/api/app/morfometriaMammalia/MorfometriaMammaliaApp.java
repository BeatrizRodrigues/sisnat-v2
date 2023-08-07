package com.sisnat.api.app.morfometriaMammalia;

import com.sisnat.api.app.morfometriaMammalia.dto.MorfMammaliaConverterDTO;
import com.sisnat.api.app.morfometriaMammalia.dto.MorfMammaliaDTO;
import com.sisnat.api.app.morfometriaMammalia.dto.MorfMammaliaFindDTO;
import com.sisnat.api.app.morfometriaMammalia.dto.MorfMammaliaInputDTO;
import com.sisnat.domain.ClassesAnimais;
import com.sisnat.domain.animal.service.AnimalService;
import com.sisnat.domain.morfometriaMammalia.MorfometriaMammalia;
import com.sisnat.domain.morfometriaMammalia.service.MorfometriaMammaliaService;
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
public class MorfometriaMammaliaApp {

    private final MorfMammaliaConverterDTO conversor;
    private final MorfometriaMammaliaService service;
    private final AnimalService animalService;

    @Transactional(readOnly = true)
    public MorfMammaliaDTO buscarPorID(final Long codigo) {
        log.debug("c=MorfometriaMammaliaApp, m=buscarPorID, codigo={}", codigo);

        final MorfometriaMammalia resultado = service.buscarPorId(codigo);

        return conversor.converter(resultado);
    }

    @Transactional
    public MorfMammaliaDTO deletarPorID(final Long id) {
        log.debug("c=MorfometriaMammaliaApp, m=deletarPorID, id={}", id);

        final MorfometriaMammalia resultado = service.deletarPorID(id);

        return conversor.converter(resultado);
    }

    @Transactional
    public MorfMammaliaDTO salvarMorfometriaMammalia(final MorfMammaliaInputDTO dto) {
        log.debug("c=MorfometriaMammaliaApp, m=salvarMorfometriaMammalia, dto={}", dto);

        final var buscaAnimal = animalService.buscarPorId(dto.getAnimal().getCodigo());
        MorfometriaMammalia resultado = new MorfometriaMammalia();

        if (buscaAnimal.getClasse().equals(ClassesAnimais.MAMMALIA)){
            resultado = service.salvarMorfometriaMammalia(dto.toModel());
        } else {
            log.error("Falha ao salvar Morfometria, Animal não pertence à classe Mammalia.", buscaAnimal);
            NexusNotFoundException.of("O Animal não pertence à classe Mammalia.");
        }

        return conversor.converter(resultado);
    }

    @Transactional
    public MorfMammaliaDTO atualizarMorfometriaMammalia(final Long id, final MorfMammaliaInputDTO dto) {
        log.debug("c=MorfometriaMammaliaApp, m=atualizarMorfometriaMammalia, id={}, dto={}", id, dto);

        final MorfometriaMammalia resultado = service.atualizarMorfometriaMammalia(id, dto.toModel());

        return conversor.converter(resultado);
    }

    @Transactional(readOnly = true)
    public ResponseList<MorfMammaliaDTO> buscarTodosMorfometriaMammalia(final MorfMammaliaFindDTO dto) {
        log.debug("c=MorfometriaMammaliaApp, m=buscarTodos, dto={}", dto);

        final var request = new PaginationRequest<>(dto.generateWhere(), dto.generatePagination());
        final var response = service.buscarTodosMorfometriaMammalia(request);
        final var data = converterDTO(response.getData(), dto);

        return new ResponseList<>(data, response);
    }

    private List<MorfMammaliaDTO> converterDTO(final List<MorfometriaMammalia> morfometriaMammalia, final MorfMammaliaFindDTO dto) {
        final var fields = nonNull(dto.getFields()) ? dto.getFields() : new ArrayList<String>();

        final var dtos = morfometriaMammalia.stream().map(p -> {
            final var builder = MorfometriaMammalia.builder();

            builder.id(p.getId());

            if (fields.isEmpty() || fields.contains("dataMorf")) {
                builder.dataMorf(p.getDataMorf());
            }

            if (fields.isEmpty() || fields.contains("peso")) {
                builder.peso(p.getPeso());
            }

            if (fields.isEmpty() || fields.contains("cra")) {
                builder.cra(p.getCra());
            }

            if (fields.isEmpty() || fields.contains("cc")) {
                builder.cc(p.getCc());
            }

            if (fields.isEmpty() || fields.contains("cm")) {
                builder.cm(p.getCm());
            }

            if (fields.isEmpty() || fields.contains("cp")) {
                builder.cp(p.getCp());
            }

            if (fields.isEmpty() || fields.contains("ho")) {
                builder.ho(p.getHo());
            }

            if (fields.isEmpty() || fields.contains("animal")) {
                builder.animal(p.getAnimal());
            }

            return new MorfMammaliaDTO(builder.build());

        }).collect(Collectors.toList());

        return dtos;
    }
}
