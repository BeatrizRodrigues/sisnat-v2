package com.sisnat.domain.animal.service;

import com.sisnat.domain.animal.Animal;
import com.sisnat.domain.animal.infra.IAnimalRepository;
import com.sisnat.util.error.ErrorMessage;
import com.sisnat.util.error.ErrorStack;
import com.sisnat.util.exception.NexusException;
import com.sisnat.util.exception.NexusNotFoundException;
import com.sisnat.util.message.MessageStack;
import com.sisnat.util.pagination.PaginationRequest;
import com.sisnat.util.pagination.ResponseList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnimalService {

    private final IAnimalRepository repository;
    private final ErrorStack errorStack;
    private final MessageStack messageStack;

    public Animal buscarPorId(final Long id) {
        log.debug("c=AnimalService, m=buscarPorId, id={}", id);

        final var buscaAnimal = repository.buscarPorId(id);

        if (isNull(buscaAnimal)) {
            NexusNotFoundException.of("Animal não encontrado.");
        }

        return buscaAnimal;
    }

    public Animal deletarPorID(final Long id) {
        log.debug("c=AnimalService, m=deletarPorID, id={}", id);
        final var resultado = repository.deletarPorID(this.buscarPorId(id));

        log.debug("Animal {} excluído com sucesso", resultado);
        messageStack.addMensage("Animal com o id " + resultado.getId() + " excluído com sucesso.");

        return resultado;
    }

    public Animal salvarAnimal(final Animal animal) {
        log.debug("c=AnimalService, m=salvarAnimal, animal={}", animal);
        if (!this.validar(animal)) {
            log.error("Falha ao salvar Animal {}", animal);
            NexusException.of("Falha ao salvar Animal", errorStack.getFalha());
        }

        final var animalSalvo = repository.salvarAnimal(animal);
        log.debug("Animal {} salvo com sucesso", animalSalvo);
        messageStack.addMensage("Animal salvo com sucesso.");

        return animalSalvo;
    }

    public Animal atualizarAnimal(final Long id, final Animal animal) {
        log.debug("c=AnimalService, m=atualizarAnimal, id={}, animal={}", id, animal);
        final var buscaAnimal = this.buscarPorId(id);

        final var animalAtualizar = buscaAnimal
                .toBuilder()
                .classe(animal.getClasse())
                .nomeCientifico(animal.getNomeCientifico())
                .nomePopular(animal.getNomePopular())
                .sexo(animal.getSexo())
                .status(animal.getStatus())
                .faixaEtaria(animal.getFaixaEtaria())
                .condicaoFisica(animal.getCondicaoFisica())
                .procedencia(animal.getProcedencia())
                .motivo(animal.getMotivo())
                .bo(animal.getBo())
                .municipioDeOrigem(animal.getMunicipioDeOrigem())
                .endereco(animal.getEndereco())
                .coordenadas(animal.getCoordenadas())
                .nomeDoador(animal.getNomeDoador())
                .telefone(animal.getTelefone())
                .areaDoResgate(animal.getAreaDoResgate())
                .dataEntrada(animal.getDataEntrada())
                .dataNascimento(animal.getDataNascimento())
                .observacao(animal.getObservacao())
                .build();


        if (!this.validar(animalAtualizar)) {
            log.error("Falha ao Atualizar Animal {}", animal);
            NexusException.of("Falha ao Atualizar Animal", errorStack.getFalha());
        }

        final var animalSalva = repository.atualizarAnimal(animalAtualizar);
        log.debug("Animal {} atualizado com sucesso", animalSalva);
        messageStack.addMensage("Animal atualizado com sucesso.");

        return animalSalva;
    }

    public ResponseList<Animal> buscarTodosAnimais(final PaginationRequest<Animal> pagination) {
        log.debug("c=AnimalService, m=buscarTodosAnimal, pagination={}", pagination);

        return repository.buscarTodosAnimal(pagination);
    }

    private Boolean validar(final Animal animal) {
        log.debug("c=AnimalService, m=validar, animal={}", animal);

        var validado = TRUE;

        if (isNull(animal.getClasse())) {
            log.error("Classe é Obrigatório [{}]", animal);
            errorStack.addError(new ErrorMessage("Classe é obrigatório"));

            validado = FALSE;
        }

        if (isNull(animal.getNomeCientifico())) {
            log.error("Nome Cientifico é Obrigatório [{}]", animal);
            errorStack.addError(new ErrorMessage("Nome Cientifico é obrigatório"));

            validado = FALSE;
        }

        if (isNull(animal.getNomePopular())) {
            log.error("Nome Popular é Obrigatório [{}]", animal);
            errorStack.addError(new ErrorMessage("Nome Popular é obrigatório"));

            validado = FALSE;
        }

        if (isNull(animal.getSexo())) {
            log.error("Sexo é Obrigatório [{}]", animal);
            errorStack.addError(new ErrorMessage("Sexo é obrigatório"));

            validado = FALSE;
        }

        if (isNull(animal.getStatus())) {
            log.error("Status é Obrigatório [{}]", animal);
            errorStack.addError(new ErrorMessage("Status é obrigatório"));

            validado = FALSE;
        }

        if (isNull(animal.getFaixaEtaria())) {
            log.error("Faixa Etaria é Obrigatório [{}]", animal);
            errorStack.addError(new ErrorMessage("Faixa Etaria é obrigatório"));

            validado = FALSE;
        }

        if (isNull(animal.getCondicaoFisica())) {
            log.error("Condicao Fisica é Obrigatório [{}]", animal);
            errorStack.addError(new ErrorMessage("Condicao Fisica é obrigatório"));

            validado = FALSE;
        }

        if (isNull(animal.getProcedencia())) {
            log.error("Procedencia é Obrigatório [{}]", animal);
            errorStack.addError(new ErrorMessage("Procedencia é obrigatório"));

            validado = FALSE;
        }

        if (isNull(animal.getMotivo())) {
            log.error("Motivo é Obrigatório [{}]", animal);
            errorStack.addError(new ErrorMessage("Motivo é obrigatório"));

            validado = FALSE;
        }

        if (isNull(animal.getMunicipioDeOrigem())) {
            log.error("Municipio DeOrigem é Obrigatório [{}]", animal);
            errorStack.addError(new ErrorMessage("Municipio DeOrigem é obrigatório"));

            validado = FALSE;
        }

        if (isNull(animal.getAreaDoResgate())) {
            log.error("Area Do Resgate é Obrigatório [{}]", animal);
            errorStack.addError(new ErrorMessage("Area Do Resgate é obrigatório"));

            validado = FALSE;
        }

        if (isNull(animal.getDataEntrada())) {
            log.error("Data de Entrada é Obrigatório [{}]", animal);
            errorStack.addError(new ErrorMessage("Data de Entrada é obrigatório"));

            validado = FALSE;
        }

        return validado;
    }
}
