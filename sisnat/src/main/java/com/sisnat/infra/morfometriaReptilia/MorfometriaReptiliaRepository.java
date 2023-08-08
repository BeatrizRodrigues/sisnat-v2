package com.sisnat.infra.morfometriaReptilia;

import com.sisnat.domain.morfometriaReptilia.MorfometriaReptilia;
import com.sisnat.domain.morfometriaReptilia.infra.IMorfometriaReptiliaRepository;
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
public class MorfometriaReptiliaRepository implements IMorfometriaReptiliaRepository {

    private final IMorfometriaReptiliaJPARepository repository;

    @Override
    public MorfometriaReptilia buscarPorId(Long id) {
        log.debug("c=MorfometriaReptiliaRepository, m=buscarPorID, id={}", id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public MorfometriaReptilia deletarPorID(MorfometriaReptilia morfometriaReptilia) {
        log.debug("c=MorfometriaReptiliaRepository, m=deletarPorID, morfometriaReptilia={}", morfometriaReptilia);
        repository.delete(morfometriaReptilia);
        return morfometriaReptilia;
    }

    @Override
    public MorfometriaReptilia salvarMorfometriaReptilia(MorfometriaReptilia morfometriaReptilia) {
        log.debug("c=MorfometriaReptiliaRepository, m=salvarMorfometriaReptilia, morfometriaReptilia={}", morfometriaReptilia);
        return repository.save(morfometriaReptilia);
    }

    @Override
    public MorfometriaReptilia atualizarMorfometriaReptilia(MorfometriaReptilia morfometriaReptilia) {
        log.debug("c=MorfometriaReptiliaRepository, m=atualizarMorfometriaReptilia, morfometriaReptilia={}", morfometriaReptilia);
        return repository.save(morfometriaReptilia);
    }

    @Override
    public ResponseList<MorfometriaReptilia> buscarTodosMorfometriaReptilia(PaginationRequest<MorfometriaReptilia> pagination) {
        log.debug("c=MorfometriaReptiliaRepository, m=buscarTodosMorfometriaReptilia, morfometriaReptilia={}", pagination);
        final Page<MorfometriaReptilia> page;

        if(nonNull(pagination.getWhere())){
            page = repository.findAll(pagination.getWhere(), pagination.getPage());
        }else{
            page = repository.findAll(pagination.getPage());
        }
        return new ResponseList<>(page.getContent(), page.getNumber() + 1, page.getSize(), page.getTotalPages(), page.getTotalElements());
    }
}
