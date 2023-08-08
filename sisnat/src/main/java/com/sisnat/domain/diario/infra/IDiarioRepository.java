package com.sisnat.domain.diario.infra;

import com.sisnat.domain.diario.Diario;
import com.sisnat.util.pagination.PaginationRequest;
import com.sisnat.util.pagination.ResponseList;

public interface IDiarioRepository {

    Diario buscarPorId(final Long id);

    Diario deletarPorID(final Diario diario);

    Diario salvarDiario(final Diario diario);

    Diario atualizarDiario(final Diario diario);

    ResponseList<Diario> buscarTodosDiario(final PaginationRequest<Diario> pagination);
}
