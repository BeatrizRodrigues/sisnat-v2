package com.sisnat.infra.animal;

import com.sisnat.domain.animal.Animal;
import com.sisnat.domain.animal.infra.IAnimalRepository;
import com.sisnat.util.pagination.PaginationRequest;
import com.sisnat.util.pagination.ResponseList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import static java.util.Objects.nonNull;

@Slf4j
@Repository
@RequiredArgsConstructor
public class AnimalRepository implements IAnimalRepository {
    
    private final IAnimalJPARepository repository;

    @Override
    public Animal buscarPorId(Long id) {
        log.debug("c=AnimalRepository, m=buscarPorId, id={}", id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public Animal deletarPorID(Animal animal) {
        log.debug("c=AnimalRepository, m=deletarPorID, animal={}", animal);
        repository.delete(animal);
        return animal;
    }

    @Override
    public Animal salvarAnimal(Animal animal) {
        log.debug("c=AnimalRepository, m=salvarAnimal, animal={}", animal);
        return repository.save(animal);
    }

    @Override
    public Animal atualizarAnimal(Animal animal) {
        log.debug("c=AnimalRepository, m=atualizarAnimal, animal={}", animal);
        return repository.save(animal);
    }

    @Override
    public ResponseList<Animal> buscarTodosAnimal(PaginationRequest<Animal> pagination) {
        log.debug("c=AnimalRepository, m=buscarTodosAnimal, pagination={}", pagination);
        final Page<Animal> page;

        if(nonNull(pagination.getWhere())){
            page = repository.findAll(pagination.getWhere(), pagination.getPage());
        }else{
            page = repository.findAll(pagination.getPage());
        }
        return new ResponseList<>(page.getContent(), page.getNumber() + 1, page.getSize(), page.getTotalPages(), page.getTotalElements());
    }
}
