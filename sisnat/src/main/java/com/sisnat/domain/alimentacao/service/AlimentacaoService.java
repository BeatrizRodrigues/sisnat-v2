package com.sisnat.domain.alimentacao.service;

import com.sisnat.domain.alimentacao.Alimentacao;
import com.sisnat.domain.alimentacao.infra.IAlimentacaoRepository;
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
public class AlimentacaoService {

    private final IAlimentacaoRepository repository;
    private final ErrorStack errorStack;
    private final MessageStack messageStack;

    public Alimentacao buscarPorId(final Long id) {
        log.debug("c=AlimentacaoService, m=buscarPorId, id={}", id);

        final var buscaAlimentacao = repository.buscarPorId(id);

        if (isNull(buscaAlimentacao)) {
            NexusNotFoundException.of("Alimentação não encontrada.");
        }

        return buscaAlimentacao;
    }

    public Alimentacao deletarPorID(final Long id) {
        log.debug("c=AlimentacaoService, m=deletarPorID, id={}", id);
        final var resultado = repository.deletarPorID(this.buscarPorId(id));

        log.debug("Alimentação {} excluída com sucesso", resultado);
        messageStack.addMensage("Alimentação com o id " + resultado.getId() + " excluída com sucesso.");

        return resultado;
    }

    public Alimentacao salvarAlimentacao(final Alimentacao alimentacao) {
        log.debug("c=AlimentacaoService, m=salvarAlimentacao, alimentacao={}", alimentacao);
        if (!this.validar(alimentacao)) {
            log.error("Falha ao salvar Alimentação {}", alimentacao);
            NexusException.of("Falha ao salvar Alimentação", errorStack.getFalha());
        }

        final var alimentacaoSalvo = repository.salvarAlimentacao(alimentacao);
        log.debug("Alimentação {} salvo com sucesso", alimentacaoSalvo);
        messageStack.addMensage("Alimentação salvo com sucesso.");

        return alimentacaoSalvo;
    }

    public Alimentacao atualizarAlimentacao(final Long id, final Alimentacao alimentacao) {
        log.debug("c=AlimentacaoService, m=atualizarAlimentacao, id={}, alimentacao={}", id, alimentacao);
        final var buscaAlimentacao = this.buscarPorId(id);

        final var alimentacaoAtualizar = buscaAlimentacao
                .toBuilder()
                .quantidade(alimentacao.getQuantidade())
                .alimento(alimentacao.getAlimento())
                .build();


        if (!this.validar(alimentacaoAtualizar)) {
            log.error("Falha ao Atualizar Alimentacao {}", alimentacao);
            NexusException.of("Falha ao Atualizar Alimentação", errorStack.getFalha());
        }

        final var alimentacaoSalva = repository.atualizarAlimentacao(alimentacaoAtualizar);
        log.debug("Alimentação {} atualizada com sucesso", alimentacaoSalva);
        messageStack.addMensage("Alimentação atualizada com sucesso.");

        return alimentacaoSalva;
    }

    public ResponseList<Alimentacao> buscarTodosAlimentacao(final PaginationRequest<Alimentacao> pagination) {
        log.debug("c=AlimentacaoService, m=buscarTodosAlimentacao, pagination={}", pagination);

        return repository.buscarTodosAlimentacao(pagination);
    }

    private Boolean validar(final Alimentacao alimentacao) {
        log.debug("c=AlimentacaoService, m=validar, alimentacao={}", alimentacao);

        var validado = TRUE;

        if (isNull(alimentacao.getQuantidade())) {
            log.error("Quantidade é Obrigatório [{}]", alimentacao);
            errorStack.addError(new ErrorMessage("Quantidade é obrigatório"));

            validado = FALSE;
        }

        if (isNull(alimentacao.getAlimento())) {
            log.error("Alimento é Obrigatório [{}]", alimentacao);
            errorStack.addError(new ErrorMessage("Alimento é obrigatório"));

            validado = FALSE;
        }

        return validado;
    }

}
