package com.sisnat.infra.morfometriaAves;

import com.sisnat.domain.morfometriaAves.MorfometriaAves;
import com.sisnat.domain.morfometriaAves.infra.IMorfometriaAvesRepository;
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
public class MorfometriaAvesRepository implements IMorfometriaAvesRepository {

    private final IMorfometriaAvesJPARepository repository;

    @Override
    public MorfometriaAves buscarPorId(Long id) {
        log.debug("c=MorfometriaAvesRepository, m=buscarPorID, id={}", id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public MorfometriaAves deletarPorID(MorfometriaAves morfometriaAves) {
        log.debug("c=MorfometriaAvesRepository, m=deletarPorID, morfometriaAves={}", morfometriaAves);
        repository.delete(morfometriaAves);
        return morfometriaAves;
    }

    @Override
    public MorfometriaAves salvarMorfometriaAves(MorfometriaAves morfometriaAves) {
        log.debug("c=MorfometriaAvesRepository, m=salvarMorfometriaAves, morfometriaAves={}", morfometriaAves);
        return repository.save(morfometriaAves);
    }

    @Override
    public MorfometriaAves atualizarMorfometriaAves(MorfometriaAves morfometriaAves) {
        log.debug("c=MorfometriaAvesRepository, m=atualizarMorfometriaAves, morfometriaAves={}", morfometriaAves);
        return repository.save(morfometriaAves);
    }

    @Override
    public ResponseList<MorfometriaAves> buscarTodosMorfometriaAves(PaginationRequest<MorfometriaAves> pagination) {
        log.debug("c=MorfometriaAvesRepository, m=buscarTodosMorfometriaAves, pagination={}", pagination);
        final Page<MorfometriaAves> page;

        if(nonNull(pagination.getWhere())){
            page = repository.findAll(pagination.getWhere(), pagination.getPage());
        }else{
            page = repository.findAll(pagination.getPage());
        }
        return new ResponseList<>(page.getContent(), page.getNumber() + 1, page.getSize(), page.getTotalPages(), page.getTotalElements());
    }
}
