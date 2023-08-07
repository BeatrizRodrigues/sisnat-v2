package com.sisnat.domain.statusAnimal.infra;

import com.sisnat.domain.statusAnimal.StatusAnimal;
import com.sisnat.util.pagination.PaginationRequest;
import com.sisnat.util.pagination.ResponseList;

public interface IStatusAnimalRepository {

    StatusAnimal buscarPorId(final Long id);

    StatusAnimal deletarPorID(final StatusAnimal statusAnimal);

    StatusAnimal salvarStatusAnimal(final StatusAnimal statusAnimal);

    StatusAnimal atualizarStatusAnimal(final StatusAnimal statusAnimal);

    ResponseList<StatusAnimal> buscarTodosStatusAnimal(final PaginationRequest<StatusAnimal> pagination);
}
