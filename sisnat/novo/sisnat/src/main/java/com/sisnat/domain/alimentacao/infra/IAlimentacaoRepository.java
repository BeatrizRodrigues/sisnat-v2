package com.sisnat.domain.alimentacao.infra;

import com.sisnat.domain.alimentacao.Alimentacao;
import com.sisnat.util.pagination.PaginationRequest;
import com.sisnat.util.pagination.ResponseList;

public interface IAlimentacaoRepository {

    Alimentacao buscarPorId(final Long id);

    Alimentacao deletarPorID(final Alimentacao alimentacao);

    Alimentacao salvarAlimentacao(final Alimentacao alimentacao);

    Alimentacao atualizarAlimentacao(final Alimentacao alimentacao);

    ResponseList<Alimentacao> buscarTodosAlimentacao(final PaginationRequest<Alimentacao> pagination);
}
