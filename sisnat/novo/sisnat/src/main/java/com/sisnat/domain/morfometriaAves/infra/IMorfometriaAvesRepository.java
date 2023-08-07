package com.sisnat.domain.morfometriaAves.infra;

import com.sisnat.domain.morfometriaAves.MorfometriaAves;
import com.sisnat.util.pagination.PaginationRequest;
import com.sisnat.util.pagination.ResponseList;

public interface IMorfometriaAvesRepository {

    MorfometriaAves buscarPorId(final Long id);

    MorfometriaAves deletarPorID(final MorfometriaAves morfometriaAves);

    MorfometriaAves salvarMorfometriaAves(final MorfometriaAves morfometriaAves);

    MorfometriaAves atualizarMorfometriaAves(final MorfometriaAves morfometriaAves);

    ResponseList<MorfometriaAves> buscarTodosMorfometriaAves(final PaginationRequest<MorfometriaAves> pagination);

}
