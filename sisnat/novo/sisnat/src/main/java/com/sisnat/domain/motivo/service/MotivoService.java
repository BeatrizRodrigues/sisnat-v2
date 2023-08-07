package com.sisnat.domain.motivo.service;

import com.sisnat.domain.motivo.Motivo;
import com.sisnat.domain.motivo.infra.IMotivoRepository;
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
public class MotivoService {

    private final IMotivoRepository repository;
    private final ErrorStack errorStack;
    private final MessageStack messageStack;

    public Motivo buscarPorId(final Long id) {
        log.debug("c=MotivoService, m=buscarPorId, id={}", id);

        final var buscaMotivo = repository.buscarPorId(id);

        if (isNull(buscaMotivo)) {
            NexusNotFoundException.of("Motivo não encontrado.");
        }

        return buscaMotivo;
    }

    public Motivo deletarPorID(final Long id) {
        log.debug("c=MotivoService, m=deletarPorID, id={}", id);
        final var resultado = repository.deletarPorID(this.buscarPorId(id));

        log.debug("Motivo {} excluído com sucesso", resultado);
        messageStack.addMensage("Motivo com o id " + resultado.getId() + " excluído com sucesso.");

        return resultado;
    }

    public Motivo salvarMotivo(final Motivo motivo) {
        log.debug("c=MotivoService, m=salvarMotivo, motivo={}", motivo);
        if (!this.validar(motivo)) {
            log.error("Falha ao salvar Motivo {}", motivo);
            NexusException.of("Falha ao salvar Motivo", errorStack.getFalha());
        }

        final var motivoSalvo = repository.salvarMotivo(motivo);
        log.debug("Motivo {} salvo com sucesso", motivoSalvo);
        messageStack.addMensage("Motivo salvo com sucesso.");

        return motivoSalvo;
    }

    public Motivo atualizarMotivo(final Long id, final Motivo motivo) {
        log.debug("c=MotivoService, m=atualizarMotivo, id={}, motivo={}", id, motivo);
        final var buscaMotivo = this.buscarPorId(id);

        final var motivoAtualizar = buscaMotivo
                .toBuilder()
                .motivo(motivo.getMotivo())
                .build();


        if (!this.validar(motivoAtualizar)) {
            log.error("Falha ao Atualizar Motivo {}", motivo);
            NexusException.of("Falha ao Atualizar Motivo", errorStack.getFalha());
        }

        final var motivoSalva = repository.atualizarMotivo(motivoAtualizar);
        log.debug("Motivo {} atualizado com sucesso", motivoSalva);
        messageStack.addMensage("Motivo atualizado com sucesso.");

        return motivoSalva;
    }

    public ResponseList<Motivo> buscarTodosMotivo(final PaginationRequest<Motivo> pagination) {
        log.debug("c=MotivoService, m=buscarTodosMotivo, pagination={}", pagination);

        return repository.buscarTodosMotivo(pagination);
    }

    private Boolean validar(final Motivo motivo) {
        log.debug("c=MotivoService, m=validar, motivo={}", motivo);

        var validado = TRUE;

        if (isNull(motivo.getMotivo())) {
            log.error("Motivo é Obrigatório [{}]", motivo);
            errorStack.addError(new ErrorMessage("Motivo é obrigatório"));

            validado = FALSE;
        }

        return validado;
    }
}
