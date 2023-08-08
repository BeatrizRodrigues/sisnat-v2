package com.sisnat.domain.morfometriaMammalia.service;

import com.sisnat.domain.morfometriaMammalia.MorfometriaMammalia;
import com.sisnat.domain.morfometriaMammalia.infra.IMorfometriaMammaliaRepository;
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
public class MorfometriaMammaliaService {

    private final IMorfometriaMammaliaRepository repository;
    private final ErrorStack errorStack;
    private final MessageStack messageStack;

    public MorfometriaMammalia buscarPorId(final Long id) {
        log.debug("c=MorfometriaMammaliaService, m=buscarPorId, id={}", id);

        final var buscaMorfometriaMammalia = repository.buscarPorId(id);

        if (isNull(buscaMorfometriaMammalia)) {
            NexusNotFoundException.of("Morfometria Mammalia não encontrada.");
        }

        return buscaMorfometriaMammalia;
    }

    public MorfometriaMammalia deletarPorID(final Long id) {
        log.debug("c=MorfometriaMammaliaService, m=deletarPorID, id={}", id);
        final var resultado = repository.deletarPorID(this.buscarPorId(id));

        log.debug("Morfometria Mammalia {} excluída com sucesso", resultado);
        messageStack.addMensage("Morfometria Mammalia com o id " + resultado.getId() + " excluída com sucesso.");

        return resultado;
    }

    public MorfometriaMammalia salvarMorfometriaMammalia(final MorfometriaMammalia morfometriaMammalia) {
        log.debug("c=MorfometriaMammaliaService, m=salvarMorfometriaMammalia, morfometriaMammalia={}", morfometriaMammalia);
        if (!this.validar(morfometriaMammalia)) {
            log.error("Falha ao salvar Morfometria Mammalia {}", morfometriaMammalia);
            NexusException.of("Falha ao salvar Morfometria Mammalia", errorStack.getFalha());
        }

        final var morfometriaMammaliaSalvo = repository.salvarMorfometriaMammalia(morfometriaMammalia);
        log.debug("Morfometria Mammalia {} salva com sucesso", morfometriaMammaliaSalvo);
        messageStack.addMensage("Morfometria Mammalia salva com sucesso.");

        return morfometriaMammaliaSalvo;
    }

    public MorfometriaMammalia atualizarMorfometriaMammalia(final Long id, final MorfometriaMammalia morfometriaMammalia) {
        log.debug("c=MorfometriaMammaliaService, m=atualizarMorfometriaMammalia, id={}, morfometriaMammalia={}", id, morfometriaMammalia);
        final var buscaMorfometriaMammalia = this.buscarPorId(id);

        final var morfometriaMammaliaAtualizar = buscaMorfometriaMammalia
                .toBuilder()
                .peso(morfometriaMammalia.getPeso())
                .cra(morfometriaMammalia.getCra())
                .cc(morfometriaMammalia.getCc())
                .cm(morfometriaMammalia.getCm())
                .cp(morfometriaMammalia.getCp())
                .ho(morfometriaMammalia.getHo())
                .build();


        if (!this.validar(morfometriaMammaliaAtualizar)) {
            log.error("Falha ao Atualizar Morfometria Mammalia {}", morfometriaMammalia);
            NexusException.of("Falha ao Atualizar Morfometria Mammalia", errorStack.getFalha());
        }

        final var morfometriaMammaliaSalva = repository.atualizarMorfometriaMammalia(morfometriaMammaliaAtualizar);
        log.debug("Morfometria Mammalia {} atualizada com sucesso", morfometriaMammaliaSalva);
        messageStack.addMensage("Morfometria Mammalia atualizada com sucesso.");

        return morfometriaMammaliaSalva;
    }

    public ResponseList<MorfometriaMammalia> buscarTodosMorfometriaMammalia(final PaginationRequest<MorfometriaMammalia> pagination) {
        log.debug("c=MorfometriaMammaliaService, m=buscarTodosMorfometriaMammalia, pagination={}", pagination);

        return repository.buscarTodosMorfometriaMammalia(pagination);
    }

    private Boolean validar(final MorfometriaMammalia morfometriaMammalia) {
        log.debug("c=MorfometriaMammaliaService, m=validar, MorfometriaMammalia={}", morfometriaMammalia);

        var validado = TRUE;

        if (isNull(morfometriaMammalia.getPeso())) {
            log.error("Peso é Obrigatório [{}]", morfometriaMammalia);
            errorStack.addError(new ErrorMessage("Peso é obrigatório"));

            validado = FALSE;
        }
        if (isNull(morfometriaMammalia.getCra())) {
            log.error("C.R.A é Obrigatório [{}]", morfometriaMammalia);
            errorStack.addError(new ErrorMessage("C.R.A é obrigatório"));

            validado = FALSE;
        }
        if (isNull(morfometriaMammalia.getCc())) {
            log.error("C.C é Obrigatório [{}]", morfometriaMammalia);
            errorStack.addError(new ErrorMessage("C.C é obrigatório"));

            validado = FALSE;
        }
        if (isNull(morfometriaMammalia.getCm())) {
            log.error("C.M é Obrigatório [{}]", morfometriaMammalia);
            errorStack.addError(new ErrorMessage("C.M é obrigatório"));

            validado = FALSE;
        }
        if (isNull(morfometriaMammalia.getCp())) {
            log.error("C.P é Obrigatório [{}]", morfometriaMammalia);
            errorStack.addError(new ErrorMessage("C.P é obrigatório"));

            validado = FALSE;
        }
        if (isNull(morfometriaMammalia.getHo())) {
            log.error("H.O é Obrigatório [{}]", morfometriaMammalia);
            errorStack.addError(new ErrorMessage("H.O é obrigatório"));

            validado = FALSE;
        }

        return validado;
    }
}
