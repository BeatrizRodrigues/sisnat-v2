package com.sisnat.domain.morfometriaAves.service;

import com.sisnat.domain.morfometriaAves.MorfometriaAves;
import com.sisnat.domain.morfometriaAves.infra.IMorfometriaAvesRepository;
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
public class MorfometriaAvesService {

    private final IMorfometriaAvesRepository repository;
    private final ErrorStack errorStack;
    private final MessageStack messageStack;

    public MorfometriaAves buscarPorId(final Long id) {
        log.debug("c=MorfometriaAvesService, m=buscarPorId, id={}", id);

        final var buscaMorfometriaAves = repository.buscarPorId(id);

        if (isNull(buscaMorfometriaAves)) {
            NexusNotFoundException.of("Morfometria Aves não encontrada.");
        }

        return buscaMorfometriaAves;
    }

    public MorfometriaAves deletarPorID(final Long id) {
        log.debug("c=MorfometriaAvesService, m=deletarPorID, id={}", id);
        final var resultado = repository.deletarPorID(this.buscarPorId(id));

        log.debug("Morfometria Aves {} excluída com sucesso", resultado);
        messageStack.addMensage("Morfometria Aves com o id " + resultado.getId() + " excluída com sucesso.");

        return resultado;
    }

    public MorfometriaAves salvarMorfometriaAves(final MorfometriaAves morfometriaAves) {
        log.debug("c=MorfometriaAvesService, m=salvarMorfometriaAves, MorfometriaAves={}", morfometriaAves);
        if (!this.validar(morfometriaAves)) {
            log.error("Falha ao salvar Morfometria Aves {}", morfometriaAves);
            NexusException.of("Falha ao salvar Morfometria Aves", errorStack.getFalha());
        }

        final var morfometriaAvesSalvo = repository.salvarMorfometriaAves(morfometriaAves);
        log.debug("Morfometria Aves {} salva com sucesso", morfometriaAvesSalvo);
        messageStack.addMensage("Morfometria Aves salva com sucesso.");

        return morfometriaAvesSalvo;
    }

    public MorfometriaAves atualizarMorfometriaAves(final Long id, final MorfometriaAves morfometriaAves) {
        log.debug("c=MorfometriaAvesService, m=atualizarMorfometriaAves, id={}, MorfometriaAves={}", id, morfometriaAves);
        final var buscaMorfometriaAves = this.buscarPorId(id);

        final var morfometriaAvesAtualizar = buscaMorfometriaAves
                .toBuilder()
                .peso(morfometriaAves.getPeso())
                .cb(morfometriaAves.getCb())
                .h(morfometriaAves.getH())
                .cc(morfometriaAves.getCc())
                .ca(morfometriaAves.getCa())
                .ct(morfometriaAves.getCt())
                .build();


        if (!this.validar(morfometriaAvesAtualizar)) {
            log.error("Falha ao Atualizar Morfometria Aves {}", morfometriaAves);
            NexusException.of("Falha ao Atualizar Morfometria Aves", errorStack.getFalha());
        }

        final var morfometriaAvesSalva = repository.atualizarMorfometriaAves(morfometriaAvesAtualizar);
        log.debug("Morfometria Aves {} atualizada com sucesso", morfometriaAvesSalva);
        messageStack.addMensage("Morfometria Aves atualizada com sucesso.");

        return morfometriaAvesSalva;
    }

    public ResponseList<MorfometriaAves> buscarTodosMorfometriaAves(final PaginationRequest<MorfometriaAves> pagination) {
        log.debug("c=MorfometriaAvesService, m=buscarTodosMorfometriaAves, pagination={}", pagination);

        return repository.buscarTodosMorfometriaAves(pagination);
    }

    private Boolean validar(final MorfometriaAves morfometriaAves) {
        log.debug("c=MorfometriaAvesService, m=validar, MorfometriaAves={}", morfometriaAves);

        var validado = TRUE;

        if (isNull(morfometriaAves.getPeso())) {
            log.error("Peso é Obrigatório [{}]", morfometriaAves);
            errorStack.addError(new ErrorMessage("Peso é obrigatório"));

            validado = FALSE;
        }
        if (isNull(morfometriaAves.getCb())) {
            log.error("C.B é Obrigatório [{}]", morfometriaAves);
            errorStack.addError(new ErrorMessage("C.B é obrigatório"));

            validado = FALSE;
        }
        if (isNull(morfometriaAves.getH())) {
            log.error("H. é Obrigatório [{}]", morfometriaAves);
            errorStack.addError(new ErrorMessage("H. é obrigatório"));

            validado = FALSE;
        }
        if (isNull(morfometriaAves.getCc())) {
            log.error("C.C é Obrigatório [{}]", morfometriaAves);
            errorStack.addError(new ErrorMessage("C.C é obrigatório"));

            validado = FALSE;
        }
        if (isNull(morfometriaAves.getCa())) {
            log.error("C.A é Obrigatório [{}]", morfometriaAves);
            errorStack.addError(new ErrorMessage("C.A é obrigatório"));

            validado = FALSE;
        }
        if (isNull(morfometriaAves.getCt())) {
            log.error("C.T é Obrigatório [{}]", morfometriaAves);
            errorStack.addError(new ErrorMessage("C.T é obrigatório"));

            validado = FALSE;
        }

        return validado;
    }
}
