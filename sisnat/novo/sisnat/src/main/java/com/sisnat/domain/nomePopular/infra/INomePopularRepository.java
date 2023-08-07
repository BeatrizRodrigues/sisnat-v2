package com.sisnat.domain.nomePopular.infra;

import com.sisnat.domain.nomePopular.NomePopular;
import com.sisnat.util.pagination.PaginationRequest;
import com.sisnat.util.pagination.ResponseList;

public interface INomePopularRepository {

    NomePopular buscarPorId(final Long id);

    NomePopular deletarPorID(final NomePopular nomePopular);

    NomePopular salvarNomePopular(final NomePopular nomePopular);

    NomePopular atualizarNomePopular(final NomePopular nomePopular);

    ResponseList<NomePopular> buscarTodosNomePopular(final PaginationRequest<NomePopular> pagination);
}
