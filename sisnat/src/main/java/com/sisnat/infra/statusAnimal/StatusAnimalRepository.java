package com.sisnat.infra.statusAnimal;

import com.sisnat.domain.statusAnimal.StatusAnimal;
import com.sisnat.domain.statusAnimal.infra.IStatusAnimalRepository;
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
public class StatusAnimalRepository implements IStatusAnimalRepository {

    private final IStatusAnimalJPARepository repository;

    @Override
    public StatusAnimal buscarPorId(Long id) {
        log.debug("c=StatusAnimalRepository, m=buscarPorId, id={}", id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public StatusAnimal deletarPorID(StatusAnimal statusAnimal) {
        log.debug("c=StatusAnimalRepository, m=deletarPorID, StatusAnimal={}", statusAnimal);
        repository.delete(statusAnimal);
        return statusAnimal;
    }

    @Override
    public StatusAnimal salvarStatusAnimal(StatusAnimal statusAnimal) {
        log.debug("c=StatusAnimalRepository, m=salvarStatusAnimal, statusAnimal={}", statusAnimal);
        return repository.save(statusAnimal);
    }

    @Override
    public StatusAnimal atualizarStatusAnimal(StatusAnimal statusAnimal) {
        log.debug("c=StatusAnimalRepository, m=atualizarStatusAnimal, StatusAnimal={}", statusAnimal);
        return repository.save(statusAnimal);
    }

    @Override
    public ResponseList<StatusAnimal> buscarTodosStatusAnimal(PaginationRequest<StatusAnimal> pagination) {
        log.debug("c=StatusAnimalRepository, m=buscarTodosStatusAnimal, pagination={}", pagination);
        final Page<StatusAnimal> page;

        if(nonNull(pagination.getWhere())){
            page = repository.findAll(pagination.getWhere(), pagination.getPage());
        }else{
            page = repository.findAll(pagination.getPage());
        }
        return new ResponseList<>(page.getContent(), page.getNumber() + 1, page.getSize(), page.getTotalPages(), page.getTotalElements());
    }
}
