package com.sisnat.infra.morfometriaArachnida;

import com.sisnat.domain.morfometriaArachnida.MorfometriaArachnida;
import com.sisnat.domain.morfometriaArachnida.infra.IMorfometriaArachnidaRepository;
import com.sisnat.domain.morfometriaAves.MorfometriaAves;
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
public class MorfometriaArachnidaRepository implements IMorfometriaArachnidaRepository {

    private final IMorfometriaArachnidaJPARepository repository;

    @Override
    public MorfometriaArachnida buscarPorId(Long id) {
        log.debug("c=MorfometriaArachnidaRepository, m=buscarPorId, id={}", id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public MorfometriaArachnida deletarPorID(MorfometriaArachnida morfometriaArachnida) {
        log.debug("c=MorfometriaArachnidaRepository, m=deletarPorID, morfometriaArachnida={}", morfometriaArachnida);
        repository.delete(morfometriaArachnida);
        return morfometriaArachnida;
    }

    @Override
    public MorfometriaArachnida salvarMorfometriaArachnida(MorfometriaArachnida morfometriaArachnida) {
        log.debug("c=MorfometriaArachnidaRepository, m=salvarMorfometriaArachnida, morfometriaArachnida={}", morfometriaArachnida);
        return repository.save(morfometriaArachnida);
    }

    @Override
    public MorfometriaArachnida atualizarMorfometriaArachnida(MorfometriaArachnida morfometriaArachnida) {
        log.debug("c=MorfometriaArachnidaRepository, m=atualizarMorfometriaArachnida, morfometriaArachnida={}", morfometriaArachnida);
        return repository.save(morfometriaArachnida);
    }

    @Override
    public ResponseList<MorfometriaArachnida> buscarTodosMorfometriaArachnida(PaginationRequest<MorfometriaArachnida> pagination) {
        log.debug("c=MorfometriaArachnidaRepository, m=buscarTodosMorfometriaArachnida, pagination={}", pagination);
        final Page<MorfometriaArachnida> page;

        if(nonNull(pagination.getWhere())){
            page = repository.findAll(pagination.getWhere(), pagination.getPage());
        }else{
            page = repository.findAll(pagination.getPage());
        }
        return new ResponseList<>(page.getContent(), page.getNumber() + 1, page.getSize(), page.getTotalPages(), page.getTotalElements());
    }
}
