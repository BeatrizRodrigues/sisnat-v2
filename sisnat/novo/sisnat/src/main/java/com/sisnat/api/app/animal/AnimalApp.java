package com.sisnat.api.app.animal;

import com.sisnat.api.app.animal.dto.AnimalConverterDTO;
import com.sisnat.api.app.animal.dto.AnimalDTO;
import com.sisnat.api.app.animal.dto.AnimalFindDTO;
import com.sisnat.api.app.animal.dto.AnimalInputDTO;
import com.sisnat.domain.animal.Animal;
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
public class AnimalApp {

    private final AnimalConverterDTO conversor;
    private final AnimalService service;

    @Transactional(readOnly = true)
    public AnimalDTO buscarPorID(final Long codigo) {
        log.debug("c=AnimalApp, m=buscarPorID, codigo={}", codigo);

        final Animal resultado = service.buscarPorId(codigo);

        return conversor.converter(resultado);
    }

    @Transactional
    public AnimalDTO deletarPorID(final Long id) {
        log.debug("c=AnimalApp, m=deletarPorID, id={}", id);

        final Animal resultado = service.deletarPorID(id);

        return conversor.converter(resultado);
    }

    @Transactional
    public AnimalDTO salvarAnimal(final AnimalInputDTO dto) {
        log.debug("c=AnimalApp, m=salvarAnimal, dto={}", dto);

        final Animal resultado = service.salvarAnimal(dto.toModel());

        return conversor.converter(resultado);
    }

    @Transactional
    public AnimalDTO atualizarAnimal(final Long id, final AnimalInputDTO dto) {
        log.debug("c=AnimalApp, m=atualizarAnimal, id={}, dto={}", id, dto);

        final Animal resultado = service.atualizarAnimal(id, dto.toModel());

        return conversor.converter(resultado);
    }

    @Transactional(readOnly = true)
    public ResponseList<AnimalDTO> buscarTodosAnimais(final AnimalFindDTO dto) {
        log.debug("c=AnimalApp, m=buscarTodos, dto={}", dto);

        final var request = new PaginationRequest<>(dto.generateWhere(), dto.generatePagination());
        final var response = service.buscarTodosAnimais(request);
        final var data = converterDTO(response.getData(), dto);

        return new ResponseList<>(data, response);
    }

    private List<AnimalDTO> converterDTO(final List<Animal> animal, final AnimalFindDTO dto) {
        final var fields = nonNull(dto.getFields()) ? dto.getFields() : new ArrayList<String>();

        final var dtos = animal.stream().map(p -> {
            final var builder = Animal.builder();

            builder.id(p.getId());

            if (fields.isEmpty() || fields.contains("classe")) {
                builder.classe(p.getClasse());
            }

            if (fields.isEmpty() || fields.contains("nomeCientifico")) {
                builder.nomeCientifico(p.getNomeCientifico());
            }

            if (fields.isEmpty() || fields.contains("nomePopular")) {
                builder.nomePopular(p.getNomePopular());
            }

            if (fields.isEmpty() || fields.contains("sexo")) {
                builder.sexo(p.getSexo());
            }

            if (fields.isEmpty() || fields.contains("status")) {
                builder.status(p.getStatus());
            }

            if (fields.isEmpty() || fields.contains("faixaEtaria")) {
                builder.faixaEtaria(p.getFaixaEtaria());
            }

            if (fields.isEmpty() || fields.contains("condicaoFisica")) {
                builder.condicaoFisica(p.getCondicaoFisica());
            }

            if (fields.isEmpty() || fields.contains("procedencia")) {
                builder.procedencia(p.getProcedencia());
            }

            if (fields.isEmpty() || fields.contains("motivo")) {
                builder.motivo(p.getMotivo());
            }

            if (fields.isEmpty() || fields.contains("bo")) {
                builder.bo(p.getBo());
            }

            if (fields.isEmpty() || fields.contains("municipioDeOrigem")) {
                builder.municipioDeOrigem(p.getMunicipioDeOrigem());
            }

            if (fields.isEmpty() || fields.contains("endereco")) {
                builder.endereco(p.getEndereco());
            }

            if (fields.isEmpty() || fields.contains("coordenadas")) {
                builder.coordenadas(p.getCoordenadas());
            }

            if (fields.isEmpty() || fields.contains("nomeDoador")) {
                builder.nomeDoador(p.getNomeDoador());
            }

            if (fields.isEmpty() || fields.contains("telefone")) {
                builder.telefone(p.getTelefone());
            }

            if (fields.isEmpty() || fields.contains("areaDoResgate")) {
                builder.areaDoResgate(p.getAreaDoResgate());
            }

            if (fields.isEmpty() || fields.contains("dataEntrada")) {
                builder.dataEntrada(p.getDataEntrada());
            }

            if (fields.isEmpty() || fields.contains("dataNascimento")) {
                builder.dataNascimento(p.getDataNascimento());
            }

            if (fields.isEmpty() || fields.contains("observacao")) {
                builder.observacao(p.getObservacao());
            }

            return new AnimalDTO(builder.build());

        }).collect(Collectors.toList());

        return dtos;
    }
}
