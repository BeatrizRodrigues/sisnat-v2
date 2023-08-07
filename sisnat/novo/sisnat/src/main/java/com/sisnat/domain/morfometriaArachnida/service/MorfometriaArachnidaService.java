package com.sisnat.domain.morfometriaArachnida.service;

import com.sisnat.domain.morfometriaArachnida.MorfometriaArachnida;
import com.sisnat.domain.morfometriaArachnida.infra.IMorfometriaArachnidaRepository;
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
public class MorfometriaArachnidaService {

    private final IMorfometriaArachnidaRepository repository;
    private final ErrorStack errorStack;
    private final MessageStack messageStack;

    public MorfometriaArachnida buscarPorId(final Long id) {
        log.debug("c=MorfometriaArachnidaService, m=buscarPorId, id={}", id);

        final var buscaMorfometriaArachnida = repository.buscarPorId(id);

        if (isNull(buscaMorfometriaArachnida)) {
            NexusNotFoundException.of("Morfometria Arachnida não encontrada.");
        }

        return buscaMorfometriaArachnida;
    }

    public MorfometriaArachnida deletarPorID(final Long id) {
        log.debug("c=MorfometriaArachnidaService, m=deletarPorID, id={}", id);
        final var resultado = repository.deletarPorID(this.buscarPorId(id));

        log.debug("Morfometria Arachnida {} excluída com sucesso", resultado);
        messageStack.addMensage("Morfometria Arachnida com o id " + resultado.getId() + " excluída com sucesso.");

        return resultado;
    }

    public MorfometriaArachnida salvarMorfometriaArachnida(final MorfometriaArachnida morfometriaArachnida) {
        log.debug("c=MorfometriaArachnidaService, m=salvarMorfometriaArachnida, morfometriaArachnida={}", morfometriaArachnida);
        if (!this.validar(morfometriaArachnida)) {
            log.error("Falha ao salvar Morfometria Arachnida {}", morfometriaArachnida);
            NexusException.of("Falha ao salvar Morfometria Arachnida", errorStack.getFalha());
        }

        final var morfometriaArachnidaSalvo = repository.salvarMorfometriaArachnida(morfometriaArachnida);
        log.debug("Morfometria Arachnida {} salva com sucesso", morfometriaArachnidaSalvo);
        messageStack.addMensage("Morfometria Arachnida salva com sucesso.");

        return morfometriaArachnidaSalvo;
    }

    public MorfometriaArachnida atualizarMorfometriaArachnida(final Long id, final MorfometriaArachnida morfometriaArachnida) {
        log.debug("c=MorfometriaArachnidaService, m=atualizarMorfometriaArachnida, id={}, morfometriaArachnida={}", id, morfometriaArachnida);
        final var buscaMorfometriaArachnida = this.buscarPorId(id);

        final var morfometriaArachnidaAtualizar = buscaMorfometriaArachnida
                .toBuilder()
                .peso(morfometriaArachnida.getPeso())
                .cc(morfometriaArachnida.getCc())
                .cp(morfometriaArachnida.getCp())
                .cpp(morfometriaArachnida.getCpp())
                .build();


        if (!this.validar(morfometriaArachnidaAtualizar)) {
            log.error("Falha ao Atualizar Morfometria Arachnida {}", morfometriaArachnida);
            NexusException.of("Falha ao Atualizar Morfometria Arachnida", errorStack.getFalha());
        }

        final var morfometriaArachnidaSalva = repository.atualizarMorfometriaArachnida(morfometriaArachnidaAtualizar);
        log.debug("Morfometria Arachnida {} atualizada com sucesso", morfometriaArachnidaSalva);
        messageStack.addMensage("Morfometria Arachnida atualizada com sucesso.");

        return morfometriaArachnidaSalva;
    }

    public ResponseList<MorfometriaArachnida> buscarTodosMorfometriaArachnida(final PaginationRequest<MorfometriaArachnida> pagination) {
        log.debug("c=MorfometriaArachnidaService, m=buscarTodosMorfometriaArachnida, pagination={}", pagination);

        return repository.buscarTodosMorfometriaArachnida(pagination);
    }

    private Boolean validar(final MorfometriaArachnida morfometriaArachnida) {
        log.debug("c=MorfometriaArachnidaService, m=validar, MorfometriaArachnida={}", morfometriaArachnida);

        var validado = TRUE;

        if (isNull(morfometriaArachnida.getPeso())) {
            log.error("Peso é Obrigatório [{}]", morfometriaArachnida);
            errorStack.addError(new ErrorMessage("Peso é obrigatório"));

            validado = FALSE;
        }
        if (isNull(morfometriaArachnida.getCc())) {
            log.error("C.C é Obrigatório [{}]", morfometriaArachnida);
            errorStack.addError(new ErrorMessage("C.C é obrigatório"));

            validado = FALSE;
        }
        if (isNull(morfometriaArachnida.getCp())) {
            log.error("C.P é Obrigatório [{}]", morfometriaArachnida);
            errorStack.addError(new ErrorMessage("C.P é obrigatório"));

            validado = FALSE;
        }
        if (isNull(morfometriaArachnida.getCpp())) {
            log.error("C.P.P é Obrigatório [{}]", morfometriaArachnida);
            errorStack.addError(new ErrorMessage("C.P.P é obrigatório"));

            validado = FALSE;
        }

        return validado;
    }
}
