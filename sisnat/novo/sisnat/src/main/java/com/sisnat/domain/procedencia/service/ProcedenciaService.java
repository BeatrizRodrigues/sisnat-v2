package com.sisnat.domain.procedencia.service;

import com.sisnat.domain.procedencia.Procedencia;
import com.sisnat.domain.procedencia.infra.IProcedenciaRepository;
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
public class ProcedenciaService {

    private final IProcedenciaRepository repository;
    private final ErrorStack errorStack;
    private final MessageStack messageStack;

    public Procedencia buscarPorId(final Long id) {
        log.debug("c=ProcedenciaService, m=buscarPorId, id={}", id);

        final var buscaProcedencia = repository.buscarPorId(id);

        if (isNull(buscaProcedencia)) {
            NexusNotFoundException.of("Procedencia não encontrado.");
        }

        return buscaProcedencia;
    }

    public Procedencia deletarPorID(final Long id) {
        log.debug("c=ProcedenciaService, m=deletarPorID, id={}", id);
        final var resultado = repository.deletarPorID(this.buscarPorId(id));

        log.debug("Procedencia {} excluído com sucesso", resultado);
        messageStack.addMensage("Procedencia com o id " + resultado.getId() + " excluído com sucesso.");

        return resultado;
    }

    public Procedencia salvarProcedencia(final Procedencia procedencia) {
        log.debug("c=ProcedenciaService, m=salvarProcedencia, procedencia={}", procedencia);
        if (!this.validar(procedencia)) {
            log.error("Falha ao salvar Procedencia {}", procedencia);
            NexusException.of("Falha ao salvar Procedencia", errorStack.getFalha());
        }

        final var procedenciaSalvo = repository.salvarProcedencia(procedencia);
        log.debug("Procedencia {} salvo com sucesso", procedenciaSalvo);
        messageStack.addMensage("Procedencia salvo com sucesso.");

        return procedenciaSalvo;
    }

    public Procedencia atualizarProcedencia(final Long id, final Procedencia procedencia) {
        log.debug("c=ProcedenciaService, m=atualizarProcedencia, id={}, procedencia={}", id, procedencia);
        final var buscaProcedencia = this.buscarPorId(id);

        final var procedenciaAtualizar = buscaProcedencia
                .toBuilder()
                .procedencia(procedencia.getProcedencia())
                .build();


        if (!this.validar(procedenciaAtualizar)) {
            log.error("Falha ao Atualizar Procedencia {}", procedencia);
            NexusException.of("Falha ao Atualizar Procedencia", errorStack.getFalha());
        }

        final var ProcedenciaSalva = repository.atualizarProcedencia(procedenciaAtualizar);
        log.debug("Procedencia {} atualizado com sucesso", ProcedenciaSalva);
        messageStack.addMensage("Procedencia atualizado com sucesso.");

        return ProcedenciaSalva;
    }

    public ResponseList<Procedencia> buscarTodosProcedencia(final PaginationRequest<Procedencia> pagination) {
        log.debug("c=ProcedenciaService, m=buscarTodosProcedencia, pagination={}", pagination);

        return repository.buscarTodosProcedencia(pagination);
    }

    private Boolean validar(final Procedencia procedencia) {
        log.debug("c=ProcedenciaService, m=validar, procedencia={}", procedencia);

        var validado = TRUE;

        if (isNull(procedencia.getProcedencia())) {
            log.error("Procedencia é Obrigatório [{}]", procedencia);
            errorStack.addError(new ErrorMessage("Procedencia é obrigatório"));

            validado = FALSE;
        }

        return validado;
    }
}
