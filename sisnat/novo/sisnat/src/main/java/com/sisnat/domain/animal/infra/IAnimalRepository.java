package com.sisnat.domain.animal.infra;

import com.sisnat.domain.animal.Animal;
import com.sisnat.util.pagination.PaginationRequest;
import com.sisnat.util.pagination.ResponseList;

public interface IAnimalRepository {

    Animal buscarPorId(final Long id);

    Animal deletarPorID(final Animal animal);

    Animal salvarAnimal(final Animal animal);

    Animal atualizarAnimal(final Animal animal);

    ResponseList<Animal> buscarTodosAnimal(final PaginationRequest<Animal> pagination);
}
