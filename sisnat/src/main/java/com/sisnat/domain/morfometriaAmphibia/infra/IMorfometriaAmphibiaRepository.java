package com.sisnat.domain.morfometriaAmphibia.infra;

import com.sisnat.domain.morfometriaAmphibia.MorfometriaAmphibia;
import com.sisnat.util.pagination.PaginationRequest;
import com.sisnat.util.pagination.ResponseList;

public interface IMorfometriaAmphibiaRepository {

    MorfometriaAmphibia buscarPorId(final Long id);

    MorfometriaAmphibia deletarPorID(final MorfometriaAmphibia morfometriaAmphibia);

    MorfometriaAmphibia salvarMorfometriaAmphibia(final MorfometriaAmphibia morfometriaAmphibia);

    MorfometriaAmphibia atualizarMorfometriaAmphibia(final MorfometriaAmphibia morfometriaAmphibia);

    ResponseList<MorfometriaAmphibia> buscarTodosMorfometriaAmphibia(final PaginationRequest<MorfometriaAmphibia> pagination);
}
