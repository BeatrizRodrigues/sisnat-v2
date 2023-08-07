package com.sisnat.api.app.statusAnimal;

import com.sisnat.api.app.statusAnimal.dto.StatusAnimalConverterDTO;
import com.sisnat.api.app.statusAnimal.dto.StatusAnimalDTO;
import com.sisnat.api.app.statusAnimal.dto.StatusAnimalFindDTO;
import com.sisnat.api.app.statusAnimal.dto.StatusAnimalInputDTO;
import com.sisnat.domain.statusAnimal.StatusAnimal;
import com.sisnat.domain.statusAnimal.service.StatusAnimalService;
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
public class StatusAnimalApp {

    private final StatusAnimalConverterDTO conversor;
    private final StatusAnimalService service;

    @Transactional(readOnly = true)
    public StatusAnimalDTO buscarPorID(final Long codigo) {
        log.debug("c=StatusAnimalApp, m=buscarPorID, codigo={}", codigo);

        final StatusAnimal resultado = service.buscarPorId(codigo);

        return conversor.converter(resultado);
    }

    @Transactional
    public StatusAnimalDTO deletarPorID(final Long id) {
        log.debug("c=StatusAnimalApp, m=deletarPorID, id={}", id);

        final StatusAnimal resultado = service.deletarPorID(id);

        return conversor.converter(resultado);
    }

    @Transactional
    public StatusAnimalDTO salvarStatusAnimal(final StatusAnimalInputDTO dto) {
        log.debug("c=StatusAnimalApp, m=salvarStatusAnimal, dto={}", dto);

        final StatusAnimal resultado = service.salvarStatusAnimal(dto.toModel());

        return conversor.converter(resultado);
    }

    @Transactional
    public StatusAnimalDTO atualizarStatusAnimal(final Long id, final StatusAnimalInputDTO dto) {
        log.debug("c=StatusAnimalApp, m=atualizarStatusAnimal, id={}, dto={}", id, dto);

        final StatusAnimal resultado = service.atualizarStatusAnimal(id, dto.toModel());

        return conversor.converter(resultado);
    }

    @Transactional(readOnly = true)
    public ResponseList<StatusAnimalDTO> buscarTodosStatusAnimal(final StatusAnimalFindDTO dto) {
        log.debug("c=StatusAnimalApp, m=buscarTodos, dto={}", dto);

        final var request = new PaginationRequest<>(dto.generateWhere(), dto.generatePagination());
        final var response = service.buscarTodosStatusAnimal(request);
        final var data = converterDTO(response.getData(), dto);

        return new ResponseList<>(data, response);
    }

    private List<StatusAnimalDTO> converterDTO(final List<StatusAnimal> statusAnimals, final StatusAnimalFindDTO dto) {
        final var fields = nonNull(dto.getFields()) ? dto.getFields() : new ArrayList<String>();

        final var dtos = statusAnimals.stream().map(p -> {
            final var builder = StatusAnimal.builder();

            builder.id(p.getId());

            if (fields.isEmpty() || fields.contains("statusAnimal")) {
                builder.status(p.getStatus());
            }

            return new StatusAnimalDTO(builder.build());

        }).collect(Collectors.toList());

        return dtos;
    }
}
