package com.sisnat.domain.diario.service;

import com.sisnat.domain.diario.Diario;
import com.sisnat.domain.diario.infra.IDiarioRepository;
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
public class DiarioService {

    private final IDiarioRepository repository;
    private final ErrorStack errorStack;
    private final MessageStack messageStack;

    public Diario buscarPorId(final Long id) {
        log.debug("c=DiarioService, m=buscarPorId, id={}", id);

        final var buscaDiario = repository.buscarPorId(id);

        if (isNull(buscaDiario)) {
            NexusNotFoundException.of("Diario não encontrado.");
        }

        return buscaDiario;
    }

    public Diario deletarPorID(final Long id) {
        log.debug("c=DiarioService, m=deletarPorID, id={}", id);
        final var resultado = repository.deletarPorID(this.buscarPorId(id));

        log.debug("Diario {} excluído com sucesso", resultado);
        messageStack.addMensage("Diario com o id " + resultado.getId() + " excluído com sucesso.");

        return resultado;
    }

    public Diario salvarDiario(final Diario diario) {
        log.debug("c=DiarioService, m=salvarDiario, diario={}", diario);
        if (!this.validar(diario)) {
            log.error("Falha ao salvar Diario {}", diario);
            NexusException.of("Falha ao salvar Diario", errorStack.getFalha());
        }

        final var diarioSalvo = repository.salvarDiario(diario);
        log.debug("Diario {} salvo com sucesso", diarioSalvo);
        messageStack.addMensage("Diario salvo com sucesso.");

        return diarioSalvo;
    }

    public Diario atualizarDiario(final Long id, final Diario diario) {
        log.debug("c=DiarioService, m=atualizarDiario, id={}, diario={}", id, diario);
        final var buscaDiario = this.buscarPorId(id);

        final var diarioAtualizar = buscaDiario
                .toBuilder()
                .observacao(diario.getObservacao())
                .build();


        if (!this.validar(diarioAtualizar)) {
            log.error("Falha ao Atualizar Diario {}", diario);
            NexusException.of("Falha ao Atualizar Diario", errorStack.getFalha());
        }

        final var diarioSalva = repository.atualizarDiario(diarioAtualizar);
        log.debug("Diario {} atualizado com sucesso", diarioSalva);
        messageStack.addMensage("Diario atualizado com sucesso.");

        return diarioSalva;
    }

    public ResponseList<Diario> buscarTodosDiario(final PaginationRequest<Diario> pagination) {
        log.debug("c=DiarioService, m=buscarTodosDiario, pagination={}", pagination);

        return repository.buscarTodosDiario(pagination);
    }

    private Boolean validar(final Diario diario) {
        log.debug("c=DiarioService, m=validar, diario={}", diario);

        var validado = TRUE;

        if (isNull(diario.getObservacao())) {
            log.error("Observação é Obrigatório [{}]", diario);
            errorStack.addError(new ErrorMessage("Observação é obrigatório"));

            validado = FALSE;
        }

        return validado;
    }
}
