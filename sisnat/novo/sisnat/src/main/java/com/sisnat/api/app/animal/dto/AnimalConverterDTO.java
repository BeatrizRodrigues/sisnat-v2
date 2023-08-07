package com.sisnat.api.app.animal.dto;

import com.sisnat.domain.animal.Animal;
import com.sisnat.util.message.MessageStack;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AnimalConverterDTO {

    private final MessageStack messageStack;

    public AnimalDTO converter(final Animal animal){
        log.debug("c=AnimalConverterDTO, m=converter, animal={}", animal);

        final var dto = new AnimalDTO();

        dto.setId(animal.getId());
        dto.setClasse(animal.getClasse());
        dto.setNomeCientifico(animal.getNomeCientifico());
        dto.setNomePopular(animal.getNomePopular());
        dto.setSexo(animal.getSexo());
        dto.setStatus(animal.getStatus());
        dto.setFaixaEtaria(animal.getFaixaEtaria());
        dto.setCondicaoFisica(animal.getCondicaoFisica());
        dto.setProcedencia(animal.getProcedencia());
        dto.setMotivo(animal.getMotivo());
        dto.setBo(animal.getBo());
        dto.setMunicipioDeOrigem(animal.getMunicipioDeOrigem());
        dto.setEndereco(animal.getEndereco());
        dto.setCoordenadas(animal.getCoordenadas());
        dto.setNomeDoador(animal.getNomeDoador());
        dto.setTelefone(animal.getTelefone());
        dto.setAreaDoResgate(animal.getAreaDoResgate());
        dto.setDataEntrada(animal.getDataEntrada());
        dto.setDataNascimento(animal.getDataNascimento());
        dto.setObservacao(animal.getObservacao());

        dto.setMensagem(messageStack.getMensagem());

        return dto;
    }
}
