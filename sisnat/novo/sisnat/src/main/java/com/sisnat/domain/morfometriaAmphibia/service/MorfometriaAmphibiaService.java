package com.sisnat.domain.morfometriaAmphibia.service;

import com.sisnat.domain.morfometriaAmphibia.MorfometriaAmphibia;
import com.sisnat.domain.morfometriaAmphibia.infra.IMorfometriaAmphibiaRepository;
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
public class MorfometriaAmphibiaService {

    private final IMorfometriaAmphibiaRepository repository;
    private final ErrorStack errorStack;
    private final MessageStack messageStack;

    public MorfometriaAmphibia buscarPorId(final Long id) {
        log.debug("c=MorfometriaAmphibiaService, m=buscarPorId, id={}", id);

        final var buscaMorfometriaAmphibia = repository.buscarPorId(id);

        if (isNull(buscaMorfometriaAmphibia)) {
            NexusNotFoundException.of("Morfometria Amphibia não encontrada.");
        }

        return buscaMorfometriaAmphibia;
    }

    public MorfometriaAmphibia deletarPorID(final Long id) {
        log.debug("c=MorfometriaAmphibiaService, m=deletarPorID, id={}", id);
        final var resultado = repository.deletarPorID(this.buscarPorId(id));

        log.debug("Morfometria Amphibia {} excluída com sucesso", resultado);
        messageStack.addMensage("Morfometria Amphibia com o id " + resultado.getId() + " excluída com sucesso.");

        return resultado;
    }

    public MorfometriaAmphibia salvarMorfometriaAmphibia(final MorfometriaAmphibia morfometriaAmphibia) {
        log.debug("c=MorfometriaAmphibiaService, m=salvarMorfometriaAmphibia, morfometriaAmphibia={}", morfometriaAmphibia);
        if (!this.validar(morfometriaAmphibia)) {
            log.error("Falha ao salvar Morfometria Amphibia {}", morfometriaAmphibia);
            NexusException.of("Falha ao salvar Morfometria Amphibia", errorStack.getFalha());
        }

        final var morfometriaAmphibiaSalvo = repository.salvarMorfometriaAmphibia(morfometriaAmphibia);
        log.debug("Morfometria Amphibia {} salva com sucesso", morfometriaAmphibiaSalvo);
        messageStack.addMensage("Morfometria Amphibia salva com sucesso.");

        return morfometriaAmphibiaSalvo;
    }

    public MorfometriaAmphibia atualizarMorfometriaAmphibia(final Long id, final MorfometriaAmphibia morfometriaAmphibia) {
        log.debug("c=MorfometriaAmphibiaService, m=atualizarMorfometriaAmphibia, id={}, morfometriaAmphibia={}", id, morfometriaAmphibia);
        final var buscamorfometriaAmphibia = this.buscarPorId(id);

        final var morfometriaAmphibiaAtualizar = buscamorfometriaAmphibia
                .toBuilder()
                .peso(morfometriaAmphibia.getPeso())
                .crc(morfometriaAmphibia.getCrc())
                .cc(morfometriaAmphibia.getCc())
                .don(morfometriaAmphibia.getDon())
                .cf(morfometriaAmphibia.getCf())
                .ct(morfometriaAmphibia.getCt())
                .cta(morfometriaAmphibia.getCta())
                .cp(morfometriaAmphibia.getCp())
                .build();


        if (!this.validar(morfometriaAmphibiaAtualizar)) {
            log.error("Falha ao Atualizar Morfometria Amphibia {}", morfometriaAmphibia);
            NexusException.of("Falha ao Atualizar Morfometria Amphibia", errorStack.getFalha());
        }

        final var morfometriaAmphibiaSalva = repository.atualizarMorfometriaAmphibia(morfometriaAmphibiaAtualizar);
        log.debug("Morfometria Amphibia {} atualizada com sucesso", morfometriaAmphibiaSalva);
        messageStack.addMensage("Morfometria Amphibia atualizada com sucesso.");

        return morfometriaAmphibiaSalva;
    }

    public ResponseList<MorfometriaAmphibia> buscarTodosMorfometriaAmphibia(final PaginationRequest<MorfometriaAmphibia> pagination) {
        log.debug("c=MorfometriaAmphibiaService, m=buscarTodosMorfometriaAmphibia, pagination={}", pagination);

        return repository.buscarTodosMorfometriaAmphibia(pagination);
    }

    private Boolean validar(final MorfometriaAmphibia morfometriaAmphibia) {
        log.debug("c=MorfometriaAmphibiaService, m=validar, MorfometriaAmphibia={}", morfometriaAmphibia);

        var validado = TRUE;

        if (isNull(morfometriaAmphibia.getPeso())) {
            log.error("Peso é Obrigatório [{}]", morfometriaAmphibia);
            errorStack.addError(new ErrorMessage("Peso é obrigatório"));

            validado = FALSE;
        }
        if (isNull(morfometriaAmphibia.getCrc())) {
            log.error("C.R.C é Obrigatório [{}]", morfometriaAmphibia);
            errorStack.addError(new ErrorMessage("C.R.C é obrigatório"));

            validado = FALSE;
        }
        if (isNull(morfometriaAmphibia.getCc())) {
            log.error("C.C é Obrigatório [{}]", morfometriaAmphibia);
            errorStack.addError(new ErrorMessage("C.C é obrigatório"));

            validado = FALSE;
        }
        if (isNull(morfometriaAmphibia.getDon())) {
            log.error("D.O.M é Obrigatório [{}]", morfometriaAmphibia);
            errorStack.addError(new ErrorMessage("D.O.M é obrigatório"));

            validado = FALSE;
        }
        if (isNull(morfometriaAmphibia.getCf())) {
            log.error("C.F é Obrigatório [{}]", morfometriaAmphibia);
            errorStack.addError(new ErrorMessage("C.F é obrigatório"));

            validado = FALSE;
        }
        if (isNull(morfometriaAmphibia.getCt())) {
            log.error("C.T é Obrigatório [{}]", morfometriaAmphibia);
            errorStack.addError(new ErrorMessage("C.T é obrigatório"));

            validado = FALSE;
        }
        if (isNull(morfometriaAmphibia.getCta())) {
            log.error("C.T.A é Obrigatório [{}]", morfometriaAmphibia);
            errorStack.addError(new ErrorMessage("C.T.A é obrigatório"));

            validado = FALSE;
        }
        if (isNull(morfometriaAmphibia.getCp())) {
            log.error("C.P é Obrigatório [{}]", morfometriaAmphibia);
            errorStack.addError(new ErrorMessage("C.P é obrigatório"));

            validado = FALSE;
        }

        return validado;
    }
}
