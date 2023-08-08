package com.sisnat.domain.nomeCientifico.service;

import com.sisnat.domain.nomeCientifico.NomeCientifico;
import com.sisnat.domain.nomeCientifico.infra.INomeCientificoRepository;
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
public class NomeCientificoService {

    private final INomeCientificoRepository repository;
    private final ErrorStack errorStack;
    private final MessageStack messageStack;

    public NomeCientifico buscarPorId(final Long id) {
        log.debug("c=NomeCientificoService, m=buscarPorId, id={}", id);

        final var buscaNomeCientifico = repository.buscarPorId(id);

        if (isNull(buscaNomeCientifico)) {
            NexusNotFoundException.of("NomeCientifico não encontrado.");
        }

        return buscaNomeCientifico;
    }

    public NomeCientifico deletarPorID(final Long id) {
        log.debug("c=NomeCientificoService, m=deletarPorID, id={}", id);
        final var resultado = repository.deletarPorID(this.buscarPorId(id));

        log.debug("NomeCientifico {} excluído com sucesso", resultado);
        messageStack.addMensage("NomeCientifico com o id " + resultado.getId() + " excluído com sucesso.");

        return resultado;
    }

    public NomeCientifico salvarNomeCientifico(final NomeCientifico nomeCientifico) {
        log.debug("c=NomeCientificoService, m=salvarNomeCientifico, NomeCientifico={}", nomeCientifico);
        if (!this.validar(nomeCientifico)) {
            log.error("Falha ao salvar NomeCientifico {}", nomeCientifico);
            NexusException.of("Falha ao salvar NomeCientifico", errorStack.getFalha());
        }

        final var nomeCientificoSalvo = repository.salvarNomeCientifico(nomeCientifico);
        log.debug("NomeCientifico {} salvo com sucesso", nomeCientificoSalvo);
        messageStack.addMensage("NomeCientifico salvo com sucesso.");

        return nomeCientificoSalvo;
    }

    public NomeCientifico atualizarNomeCientifico(final Long id, final NomeCientifico nomeCientifico) {
        log.debug("c=NomeCientificoService, m=atualizarNomeCientifico, id={}, nomeCientifico={}", id, nomeCientifico);
        final var buscaNomeCientifico = this.buscarPorId(id);

        final var nomeCientificoAtualizar = buscaNomeCientifico
                .toBuilder()
                .nomeCientifico(nomeCientifico.getNomeCientifico())
                .build();


        if (!this.validar(nomeCientificoAtualizar)) {
            log.error("Falha ao Atualizar NomeCientifico {}", nomeCientifico);
            NexusException.of("Falha ao Atualizar NomeCientifico", errorStack.getFalha());
        }

        final var nomeCientificoSalva = repository.atualizarNomeCientifico(nomeCientificoAtualizar);
        log.debug("NomeCientifico {} atualizado com sucesso", nomeCientificoSalva);
        messageStack.addMensage("NomeCientifico atualizado com sucesso.");

        return nomeCientificoSalva;
    }

    public ResponseList<NomeCientifico> buscarTodosNomeCientifico(final PaginationRequest<NomeCientifico> pagination) {
        log.debug("c=NomeCientificoService, m=buscarTodosNomeCientifico, pagination={}", pagination);

        return repository.buscarTodosNomeCientifico(pagination);
    }

    private Boolean validar(final NomeCientifico nomeCientifico) {
        log.debug("c=NomeCientificoService, m=validar, nomeCientifico={}", nomeCientifico);

        var validado = TRUE;

        if (isNull(nomeCientifico.getNomeCientifico())) {
            log.error("Nome Cientifico é Obrigatório [{}]", nomeCientifico);
            errorStack.addError(new ErrorMessage("Nome Cientifico é obrigatório"));

            validado = FALSE;
        }

        return validado;
    }
}
