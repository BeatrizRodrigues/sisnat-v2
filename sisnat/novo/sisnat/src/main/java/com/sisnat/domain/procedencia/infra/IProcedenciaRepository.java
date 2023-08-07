package com.sisnat.domain.procedencia.infra;

import com.sisnat.domain.procedencia.Procedencia;
import com.sisnat.util.pagination.PaginationRequest;
import com.sisnat.util.pagination.ResponseList;

public interface IProcedenciaRepository {

    Procedencia buscarPorId(final Long id);

    Procedencia deletarPorID(final Procedencia procedencia);

    Procedencia salvarProcedencia(final Procedencia procedencia);

    Procedencia atualizarProcedencia(final Procedencia procedencia);

    ResponseList<Procedencia> buscarTodosProcedencia(final PaginationRequest<Procedencia> pagination);
}
