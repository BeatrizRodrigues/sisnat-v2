package com.sisnat.domain.nomeCientifico.infra;

import com.sisnat.domain.nomeCientifico.NomeCientifico;
import com.sisnat.util.pagination.PaginationRequest;
import com.sisnat.util.pagination.ResponseList;

public interface INomeCientificoRepository {

    NomeCientifico buscarPorId(final Long id);

    NomeCientifico deletarPorID(final NomeCientifico nomeCientifico);

    NomeCientifico salvarNomeCientifico(final NomeCientifico nomeCientifico);

    NomeCientifico atualizarNomeCientifico(final NomeCientifico nomeCientifico);

    ResponseList<NomeCientifico> buscarTodosNomeCientifico(final PaginationRequest<NomeCientifico> pagination);
}
