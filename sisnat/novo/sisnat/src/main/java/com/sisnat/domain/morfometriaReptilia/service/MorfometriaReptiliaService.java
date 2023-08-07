package com.sisnat.domain.morfometriaReptilia.service;

import com.sisnat.domain.morfometriaReptilia.MorfometriaReptilia;
import com.sisnat.domain.morfometriaReptilia.infra.IMorfometriaReptiliaRepository;
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
public class MorfometriaReptiliaService {

    private final IMorfometriaReptiliaRepository repository;
    private final ErrorStack errorStack;
    private final MessageStack messageStack;

    public MorfometriaReptilia buscarPorId(final Long id) {
        log.debug("c=MorfometriaReptiliaService, m=buscarPorId, id={}", id);

        final var buscaMorfometriaReptilia = repository.buscarPorId(id);

        if (isNull(buscaMorfometriaReptilia)) {
            NexusNotFoundException.of("Morfometria Reptilia não encontrada.");
        }

        return buscaMorfometriaReptilia;
    }

    public MorfometriaReptilia deletarPorID(final Long id) {
        log.debug("c=MorfometriaReptiliaService, m=deletarPorID, id={}", id);
        final var resultado = repository.deletarPorID(this.buscarPorId(id));

        log.debug("Morfometria Reptilia {} excluída com sucesso", resultado);
        messageStack.addMensage("Morfometria Reptilia com o id " + resultado.getId() + " excluída com sucesso.");

        return resultado;
    }

    public MorfometriaReptilia salvarMorfometriaReptilia(final MorfometriaReptilia morfometriaReptilia) {
        log.debug("c=MorfometriaReptiliaService, m=salvarMorfometriaReptilia, MorfometriaReptilia={}", morfometriaReptilia);
        if (!this.validar(morfometriaReptilia)) {
            log.error("Falha ao salvar Morfometria Reptilia {}", morfometriaReptilia);
            NexusException.of("Falha ao salvar Morfometria Reptilia", errorStack.getFalha());
        }

        final var morfometriaReptiliaSalvo = repository.salvarMorfometriaReptilia(morfometriaReptilia);
        log.debug("Morfometria Reptilia {} salva com sucesso", morfometriaReptiliaSalvo);
        messageStack.addMensage("Morfometria Reptilia salva com sucesso.");

        return morfometriaReptiliaSalvo;
    }

    public MorfometriaReptilia atualizarMorfometriaReptilia(final Long id, final MorfometriaReptilia morfometriaReptilia) {
        log.debug("c=MorfometriaReptiliaService, m=atualizarMorfometriaReptilia, id={}, MorfometriaReptilia={}", id, morfometriaReptilia);
        final var buscaMorfometriaReptilia = this.buscarPorId(id);

        final var morfometriaReptiliaAtualizar = buscaMorfometriaReptilia
                .toBuilder()
                .peso(morfometriaReptilia.getPeso())
                .crc(morfometriaReptilia.getCrc())
                .build();


        if (!this.validar(morfometriaReptiliaAtualizar)) {
            log.error("Falha ao Atualizar Morfometria Reptilia {}", morfometriaReptilia);
            NexusException.of("Falha ao Atualizar Morfometria Reptilia", errorStack.getFalha());
        }

        final var morfometriaReptiliaSalva = repository.atualizarMorfometriaReptilia(morfometriaReptiliaAtualizar);
        log.debug("Morfometria Reptilia {} atualizada com sucesso", morfometriaReptiliaSalva);
        messageStack.addMensage("Morfometria Reptilia atualizada com sucesso.");

        return morfometriaReptiliaSalva;
    }

    public ResponseList<MorfometriaReptilia> buscarTodosMorfometriaReptilia(final PaginationRequest<MorfometriaReptilia> pagination) {
        log.debug("c=MorfometriaReptiliaService, m=buscarTodosMorfometriaReptilia, pagination={}", pagination);

        return repository.buscarTodosMorfometriaReptilia(pagination);
    }

    private Boolean validar(final MorfometriaReptilia morfometriaReptilia) {
        log.debug("c=MorfometriaReptiliaService, m=validar, MorfometriaReptilia={}", morfometriaReptilia);

        var validado = TRUE;

        if (isNull(morfometriaReptilia.getPeso())) {
            log.error("Peso é Obrigatório [{}]", morfometriaReptilia);
            errorStack.addError(new ErrorMessage("Peso é obrigatório"));

            validado = FALSE;
        }
        if (isNull(morfometriaReptilia.getCrc())) {
            log.error("C.R.C é Obrigatório [{}]", morfometriaReptilia);
            errorStack.addError(new ErrorMessage("C.R.C é obrigatório"));

            validado = FALSE;
        }

        return validado;
    }
}
