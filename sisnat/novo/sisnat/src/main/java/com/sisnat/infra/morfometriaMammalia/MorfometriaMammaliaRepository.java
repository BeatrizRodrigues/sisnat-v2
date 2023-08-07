package com.sisnat.infra.morfometriaMammalia;

import com.sisnat.domain.morfometriaMammalia.MorfometriaMammalia;
import com.sisnat.domain.morfometriaMammalia.infra.IMorfometriaMammaliaRepository;
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
public class MorfometriaMammaliaRepository implements IMorfometriaMammaliaRepository {

    private final IMorfometriaMammaliaJPARepository repository;

    @Override
    public MorfometriaMammalia buscarPorId(Long id) {
        log.debug("c=MorfometriaMammaliaRepository, m=buscarPorID, id={}", id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public MorfometriaMammalia deletarPorID(MorfometriaMammalia morfometriaMammalia) {
        log.debug("c=MorfometriaMammaliaRepository, m=deletarPorID, morfometriaMammalia={}", morfometriaMammalia);
        repository.delete(morfometriaMammalia);
        return morfometriaMammalia;
    }

    @Override
    public MorfometriaMammalia salvarMorfometriaMammalia(MorfometriaMammalia morfometriaMammalia) {
        log.debug("c=MorfometriaMammaliaRepository, m=salvarMorfometriaMammalia, morfometriaMammalia={}", morfometriaMammalia);
        return repository.save(morfometriaMammalia);
    }

    @Override
    public MorfometriaMammalia atualizarMorfometriaMammalia(MorfometriaMammalia morfometriaMammalia) {
        log.debug("c=MorfometriaMammaliaRepository, m=atualizarMorfometriaMammalia, morfometriaMammalia={}", morfometriaMammalia);
        return repository.save(morfometriaMammalia);
    }

    @Override
    public ResponseList<MorfometriaMammalia> buscarTodosMorfometriaMammalia(PaginationRequest<MorfometriaMammalia> pagination) {
        log.debug("c=MorfometriaMammaliaRepository, m=buscarTodosMorfometriaMammalia, pagination={}", pagination);
        final Page<MorfometriaMammalia> page;

        if(nonNull(pagination.getWhere())){
            page = repository.findAll(pagination.getWhere(), pagination.getPage());
        }else{
            page = repository.findAll(pagination.getPage());
        }
        return new ResponseList<>(page.getContent(), page.getNumber() + 1, page.getSize(), page.getTotalPages(), page.getTotalElements());
    }
}
