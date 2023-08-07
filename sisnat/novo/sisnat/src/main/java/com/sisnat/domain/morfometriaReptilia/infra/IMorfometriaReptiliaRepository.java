package com.sisnat.domain.morfometriaReptilia.infra;

import com.sisnat.domain.morfometriaReptilia.MorfometriaReptilia;
import com.sisnat.util.pagination.PaginationRequest;
import com.sisnat.util.pagination.ResponseList;

public interface IMorfometriaReptiliaRepository {

    MorfometriaReptilia buscarPorId(final Long id);

    MorfometriaReptilia deletarPorID(final MorfometriaReptilia morfometriaReptilia);

    MorfometriaReptilia salvarMorfometriaReptilia(final MorfometriaReptilia morfometriaReptilia);

    MorfometriaReptilia atualizarMorfometriaReptilia(final MorfometriaReptilia morfometriaReptilia);

    ResponseList<MorfometriaReptilia> buscarTodosMorfometriaReptilia(final PaginationRequest<MorfometriaReptilia> pagination);

}
