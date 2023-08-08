package com.sisnat.domain.morfometriaArachnida.infra;

import com.sisnat.domain.morfometriaArachnida.MorfometriaArachnida;
import com.sisnat.util.pagination.PaginationRequest;
import com.sisnat.util.pagination.ResponseList;

public interface IMorfometriaArachnidaRepository {

    MorfometriaArachnida buscarPorId(final Long id);

    MorfometriaArachnida deletarPorID(final MorfometriaArachnida morfometriaArachnida);

    MorfometriaArachnida salvarMorfometriaArachnida(final MorfometriaArachnida morfometriaArachnida);

    MorfometriaArachnida atualizarMorfometriaArachnida(final MorfometriaArachnida morfometriaArachnida);

    ResponseList<MorfometriaArachnida> buscarTodosMorfometriaArachnida(final PaginationRequest<MorfometriaArachnida> pagination);

}
