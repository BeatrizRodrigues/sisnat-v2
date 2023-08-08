package com.sisnat.domain.atendimento.service;

import com.sisnat.domain.atendimento.Atendimento;
import com.sisnat.domain.atendimento.infra.IAtendimentoRepository;
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
public class AtendimentoService {

    private final IAtendimentoRepository repository;
    private final ErrorStack errorStack;
    private final MessageStack messageStack;

    public Atendimento buscarPorId(final Long id) {
        log.debug("c=AtendimentoService, m=buscarPorId, id={}", id);

        final var buscaAlimentacao = repository.buscarPorId(id);

        if (isNull(buscaAlimentacao)) {
            NexusNotFoundException.of("Atendimento não encontrado.");
        }

        return buscaAlimentacao;
    }

    public Atendimento deletarPorID(final Long id) {
        log.debug("c=AtendimentoService, m=deletarPorID, id={}", id);
        final var resultado = repository.deletarPorID(this.buscarPorId(id));

        log.debug("Atendimento {} excluído com sucesso", resultado);
        messageStack.addMensage("Atendimento com o id " + resultado.getId() + " excluído com sucesso.");

        return resultado;
    }

    public Atendimento salvarAtendimento(final Atendimento atendimento) {
        log.debug("c=AtendimentoService, m=salvarAtendimento, atendimento={}", atendimento);
        if (!this.validar(atendimento)) {
            log.error("Falha ao salvar Atendimento {}", atendimento);
            NexusException.of("Falha ao salvar Atendimento", errorStack.getFalha());
        }

        final var atendimentoSalvo = repository.salvarAtendimento(atendimento);
        log.debug("Atendimento {} salvo com sucesso", atendimentoSalvo);
        messageStack.addMensage("Atendimento salvo com sucesso.");

        return atendimentoSalvo;
    }

    public Atendimento atualizarAtendimento(final Long id, final Atendimento atendimento) {
        log.debug("c=AtendimentoService, m=atualizarAtendimento, id={}, atendimento={}", id, atendimento);
        final var buscaAtendimento = this.buscarPorId(id);

        final var atendimentoAtualizar = buscaAtendimento
                .toBuilder()
                .procedimento(atendimento.getProcedimento())
                .build();


        if (!this.validar(atendimentoAtualizar)) {
            log.error("Falha ao Atualizar Atendimento {}", atendimento);
            NexusException.of("Falha ao Atualizar Atendimento", errorStack.getFalha());
        }

        final var atendimentoSalvo = repository.atualizarAtendimento(atendimentoAtualizar);
        log.debug("Atendimento {} atualizado com sucesso", atendimentoSalvo);
        messageStack.addMensage("Atendimento atualizado com sucesso.");

        return atendimentoSalvo;
    }

    public ResponseList<Atendimento> buscarTodosAtendimento(final PaginationRequest<Atendimento> pagination) {
        log.debug("c=AtendimentoService, m=buscarTodosAtendimento, pagination={}", pagination);

        return repository.buscarTodosAtendimento(pagination);
    }

    private Boolean validar(final Atendimento atendimento) {
        log.debug("c=AtendimentoService, m=validar, atendimento={}", atendimento);

        var validado = TRUE;

        if (isNull(atendimento.getProcedimento())) {
            log.error("Processo é Obrigatório [{}]", atendimento);
            errorStack.addError(new ErrorMessage("Processo é obrigatório"));

            validado = FALSE;
        }

        return validado;
    }

}
