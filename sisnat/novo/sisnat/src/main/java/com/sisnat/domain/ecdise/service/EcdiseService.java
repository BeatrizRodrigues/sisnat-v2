package com.sisnat.domain.ecdise.service;

import com.sisnat.domain.ecdise.Ecdise;
import com.sisnat.domain.ecdise.infra.IEcdiseRepository;
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
public class EcdiseService {

    private final IEcdiseRepository repository;
    private final ErrorStack errorStack;
    private final MessageStack messageStack;

    public Ecdise buscarPorId(final Long id) {
        log.debug("c=EcdiseService, m=buscarPorId, id={}", id);

        final var buscaEcdise = repository.buscarPorId(id);

        if (isNull(buscaEcdise)) {
            NexusNotFoundException.of("Ecdise não encontrada.");
        }

        return buscaEcdise;
    }

    public Ecdise deletarPorID(final Long id) {
        log.debug("c=EcdiseService, m=deletarPorID, id={}", id);
        final var resultado = repository.deletarPorID(this.buscarPorId(id));

        log.debug("Ecdise {} excluída com sucesso", resultado);
        messageStack.addMensage("Ecdise com o id " + resultado.getId() + " excluída com sucesso.");

        return resultado;
    }

    public Ecdise salvarEcdise(final Ecdise ecdise) {
        log.debug("c=EcdiseService, m=salvarEcdise, ecdise={}", ecdise);
        if (!this.validar(ecdise)) {
            log.error("Falha ao salvar Ecdise {}", ecdise);
            NexusException.of("Falha ao salvar Ecdise", errorStack.getFalha());
        }

        final var ecdiseSalvo = repository.salvarEcdise(ecdise);
        log.debug("Ecdise {} salva com sucesso", ecdiseSalvo);
        messageStack.addMensage("Ecdise salva com sucesso.");

        return ecdiseSalvo;
    }

    public Ecdise atualizarEcdise(final Long id, final Ecdise ecdise) {
        log.debug("c=EcdiseService, m=atualizarEcdise, id={}, ecdise={}", id, ecdise);
        final var buscaEcdise = this.buscarPorId(id);

        final var ecdiseAtualizar = buscaEcdise
                .toBuilder()
                .ecdise(ecdise.getEcdise())
                .build();


        if (!this.validar(ecdiseAtualizar)) {
            log.error("Falha ao Atualizar Ecdise {}", ecdise);
            NexusException.of("Falha ao Atualizar Ecdise", errorStack.getFalha());
        }

        final var ecdiseSalva = repository.atualizarEcdise(ecdiseAtualizar);
        log.debug("Ecdise {} atualizada com sucesso", ecdiseSalva);
        messageStack.addMensage("Ecdise atualizada com sucesso.");

        return ecdiseSalva;
    }

    public ResponseList<Ecdise> buscarTodosEcdise(final PaginationRequest<Ecdise> pagination) {
        log.debug("c=EcdiseService, m=buscarTodosEcdise, pagination={}", pagination);

        return repository.buscarTodosEcdise(pagination);
    }

    private Boolean validar(final Ecdise ecdise) {
        log.debug("c=EcdiseService, m=validar, ecdise={}", ecdise);

        var validado = TRUE;

        if (isNull(ecdise.getEcdise())) {
            log.error("Ecdise é Obrigatório [{}]", ecdise);
            errorStack.addError(new ErrorMessage("Ecdise é obrigatório"));

            validado = FALSE;
        }

        return validado;
    }
}
