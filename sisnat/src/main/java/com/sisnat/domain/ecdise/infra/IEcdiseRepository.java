package com.sisnat.domain.ecdise.infra;

import com.sisnat.domain.ecdise.Ecdise;
import com.sisnat.util.pagination.PaginationRequest;
import com.sisnat.util.pagination.ResponseList;

public interface IEcdiseRepository {

    Ecdise buscarPorId(final Long id);

    Ecdise deletarPorID(final Ecdise ecdise);

    Ecdise salvarEcdise(final Ecdise ecdise);

    Ecdise atualizarEcdise(final Ecdise ecdise);

    ResponseList<Ecdise> buscarTodosEcdise(final PaginationRequest<Ecdise> pagination);

}
