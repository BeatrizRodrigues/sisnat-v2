package com.sisnat.domain.morfometriaMammalia.infra;

import com.sisnat.domain.morfometriaMammalia.MorfometriaMammalia;
import com.sisnat.util.pagination.PaginationRequest;
import com.sisnat.util.pagination.ResponseList;

public interface IMorfometriaMammaliaRepository {

    MorfometriaMammalia buscarPorId(final Long id);

    MorfometriaMammalia deletarPorID(final MorfometriaMammalia morfometriaMammalia);

    MorfometriaMammalia salvarMorfometriaMammalia(final MorfometriaMammalia morfometriaMammalia);

    MorfometriaMammalia atualizarMorfometriaMammalia(final MorfometriaMammalia morfometriaMammalia);

    ResponseList<MorfometriaMammalia> buscarTodosMorfometriaMammalia(final PaginationRequest<MorfometriaMammalia> pagination);
}
