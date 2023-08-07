package com.sisnat.domain.nomePopular.service;

import com.sisnat.domain.nomePopular.NomePopular;
import com.sisnat.domain.nomePopular.infra.INomePopularRepository;
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
public class NomePopularService {

    private final INomePopularRepository repository;
    private final ErrorStack errorStack;
    private final MessageStack messageStack;

    public NomePopular buscarPorId(final Long id) {
        log.debug("c=NomePopularService, m=buscarPorId, id={}", id);

        final var buscaNomePopular = repository.buscarPorId(id);

        if (isNull(buscaNomePopular)) {
            NexusNotFoundException.of("NomePopular não encontrado.");
        }

        return buscaNomePopular;
    }

    public NomePopular deletarPorID(final Long id) {
        log.debug("c=NomePopularService, m=deletarPorID, id={}", id);
        final var resultado = repository.deletarPorID(this.buscarPorId(id));

        log.debug("NomePopular {} excluído com sucesso", resultado);
        messageStack.addMensage("NomePopular com o id " + resultado.getId() + " excluído com sucesso.");

        return resultado;
    }

    public NomePopular salvarNomePopular(final NomePopular nomePopular) {
        log.debug("c=NomePopularService, m=salvarNomePopular, NomePopular={}", nomePopular);
        if (!this.validar(nomePopular)) {
            log.error("Falha ao salvar NomePopular {}", nomePopular);
            NexusException.of("Falha ao salvar NomePopular", errorStack.getFalha());
        }

        final var nomePopularSalvo = repository.salvarNomePopular(nomePopular);
        log.debug("NomePopular {} salvo com sucesso", nomePopularSalvo);
        messageStack.addMensage("NomePopular salvo com sucesso.");

        return nomePopularSalvo;
    }

    public NomePopular atualizarNomePopular(final Long id, final NomePopular nomePopular) {
        log.debug("c=NomePopularService, m=atualizarNomePopular, id={}, nomePopular={}", id, nomePopular);
        final var buscaNomePopular = this.buscarPorId(id);

        final var nomePopularAtualizar = buscaNomePopular
                .toBuilder()
                .nomePopular(nomePopular.getNomePopular())
                .nomeCientifico(nomePopular.getNomeCientifico())
                .build();


        if (!this.validar(nomePopularAtualizar)) {
            log.error("Falha ao Atualizar NomePopular {}", nomePopular);
            NexusException.of("Falha ao Atualizar NomePopular", errorStack.getFalha());
        }

        final var nomePopularSalva = repository.atualizarNomePopular(nomePopularAtualizar);
        log.debug("NomePopular {} atualizado com sucesso", nomePopularSalva);
        messageStack.addMensage("NomePopular atualizado com sucesso.");

        return nomePopularSalva;
    }

    public ResponseList<NomePopular> buscarTodosNomePopular(final PaginationRequest<NomePopular> pagination) {
        log.debug("c=NomePopularService, m=buscarTodosNomePopular, pagination={}", pagination);

        return repository.buscarTodosNomePopular(pagination);
    }

    private Boolean validar(final NomePopular nomePopular) {
        log.debug("c=NomePopularService, m=validar, nomePopular={}", nomePopular);

        var validado = TRUE;

        if (isNull(nomePopular.getNomePopular())) {
            log.error("Nome Popular é Obrigatório [{}]", nomePopular);
            errorStack.addError(new ErrorMessage("Nome Popular é obrigatório"));

            validado = FALSE;
        }

        if (isNull(nomePopular.getNomeCientifico())) {
            log.error("Nome Cientifico é Obrigatório [{}]", nomePopular);
            errorStack.addError(new ErrorMessage("Nome Cientifico é obrigatório"));

            validado = FALSE;
        }

        return validado;
    }
}
