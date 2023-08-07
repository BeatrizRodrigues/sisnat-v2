package com.sisnat.domain.statusAnimal.service;

import com.sisnat.domain.statusAnimal.StatusAnimal;
import com.sisnat.domain.statusAnimal.infra.IStatusAnimalRepository;
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
public class StatusAnimalService {

    private final IStatusAnimalRepository repository;
    private final ErrorStack errorStack;
    private final MessageStack messageStack;

    public StatusAnimal buscarPorId(final Long id) {
        log.debug("c=StatusAnimalService, m=buscarPorId, id={}", id);

        final var buscaStatusAnimal = repository.buscarPorId(id);

        if (isNull(buscaStatusAnimal)) {
            NexusNotFoundException.of("Status Animal não encontrado.");
        }

        return buscaStatusAnimal;
    }

    public StatusAnimal deletarPorID(final Long id) {
        log.debug("c=StatusAnimalService, m=deletarPorID, id={}", id);
        final var resultado = repository.deletarPorID(this.buscarPorId(id));

        log.debug("Status Animal {} excluído com sucesso", resultado);
        messageStack.addMensage("Status Animal com o id " + resultado.getId() + " excluído com sucesso.");

        return resultado;
    }

    public StatusAnimal salvarStatusAnimal(final StatusAnimal statusAnimal) {
        log.debug("c=StatusAnimalService, m=salvarStatusAnimal, StatusAnimal={}", statusAnimal);
        if (!this.validar(statusAnimal)) {
            log.error("Falha ao salvar Status Animal {}", statusAnimal);
            NexusException.of("Falha ao salvar Status Animal", errorStack.getFalha());
        }

        final var statusAnimalSalvo = repository.salvarStatusAnimal(statusAnimal);
        log.debug("StatusAnimal {} salvo com sucesso", statusAnimalSalvo);
        messageStack.addMensage("StatusAnimal salvo com sucesso.");

        return statusAnimalSalvo;
    }

    public StatusAnimal atualizarStatusAnimal(final Long id, final StatusAnimal statusAnimal) {
        log.debug("c=StatusAnimalService, m=atualizarStatusAnimal, id={}, statusAnimal={}", id, statusAnimal);
        final var buscaStatusAnimal = this.buscarPorId(id);

        final var statusAnimalAtualizar = buscaStatusAnimal
                .toBuilder()
                .status(statusAnimal.getStatus())
                .build();


        if (!this.validar(statusAnimalAtualizar)) {
            log.error("Falha ao Atualizar Status Animal {}", statusAnimal);
            NexusException.of("Falha ao Atualizar Status Animal", errorStack.getFalha());
        }

        final var statusAnimalSalva = repository.atualizarStatusAnimal(statusAnimalAtualizar);
        log.debug("Status Animal {} atualizado com sucesso", statusAnimalSalva);
        messageStack.addMensage("Status Animal atualizado com sucesso.");

        return statusAnimalSalva;
    }

    public ResponseList<StatusAnimal> buscarTodosStatusAnimal(final PaginationRequest<StatusAnimal> pagination) {
        log.debug("c=StatusAnimalService, m=buscarTodosStatusAnimal, pagination={}", pagination);

        return repository.buscarTodosStatusAnimal(pagination);
    }

    private Boolean validar(final StatusAnimal statusAnimal) {
        log.debug("c=StatusAnimalService, m=validar, statusAnimal={}", statusAnimal);

        var validado = TRUE;

        if (isNull(statusAnimal.getStatus())) {
            log.error("Status é Obrigatório [{}]", statusAnimal);
            errorStack.addError(new ErrorMessage("Status é obrigatório"));

            validado = FALSE;
        }

        return validado;
    }
}
