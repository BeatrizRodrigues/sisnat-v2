package com.sisnat.domain.motivo.infra;

import com.sisnat.domain.motivo.Motivo;
import com.sisnat.util.pagination.PaginationRequest;
import com.sisnat.util.pagination.ResponseList;

public interface IMotivoRepository {

    Motivo buscarPorId(final Long id);

    Motivo deletarPorID(final Motivo Motivo);

    Motivo salvarMotivo(final Motivo Motivo);

    Motivo atualizarMotivo(final Motivo Motivo);

    ResponseList<Motivo> buscarTodosMotivo(final PaginationRequest<Motivo> pagination);
}
