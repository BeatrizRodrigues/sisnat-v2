package com.sisnat.domain.atendimento.infra;

import com.sisnat.domain.atendimento.Atendimento;
import com.sisnat.util.pagination.PaginationRequest;
import com.sisnat.util.pagination.ResponseList;

public interface IAtendimentoRepository {

    Atendimento buscarPorId(final Long id);

    Atendimento deletarPorID(final Atendimento atendimento);

    Atendimento salvarAtendimento(final Atendimento atendimento);

    Atendimento atualizarAtendimento(final Atendimento atendimento);

    ResponseList<Atendimento> buscarTodosAtendimento(final PaginationRequest<Atendimento> pagination);

}
