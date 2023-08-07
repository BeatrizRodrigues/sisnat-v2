package com.sisnat.infra.ecdise;

import com.sisnat.domain.ecdise.Ecdise;
import com.sisnat.domain.ecdise.infra.IEcdiseRepository;
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
public class EcdiseRepository implements IEcdiseRepository {

    private final IEcdiseJPARepository repository;

    @Override
    public Ecdise buscarPorId(Long id) {
        log.debug("c=EcdiseRepository, m=buscarPorId, id={}", id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public Ecdise deletarPorID(Ecdise ecdise) {
        log.debug("c=EcdiseRepository, m=deletarPorID, ecdise={}", ecdise);
        repository.delete(ecdise);
        return ecdise;
    }

    @Override
    public Ecdise salvarEcdise(Ecdise ecdise) {
        log.debug("c=EcdiseRepository, m=salvarEcdise, ecdise={}", ecdise);
        return repository.save(ecdise);
    }

    @Override
    public Ecdise atualizarEcdise(Ecdise ecdise) {
        log.debug("c=EcdiseRepository, m=atualizarEcdise, ecdise={}", ecdise);
        return repository.save(ecdise);
    }

    @Override
    public ResponseList<Ecdise> buscarTodosEcdise(PaginationRequest<Ecdise> pagination) {
        log.debug("c=EcdiseRepository, m=buscarTodosEcdise, pagination={}", pagination);

        final Page<Ecdise> page;

        if(nonNull(pagination.getWhere())){
            page = repository.findAll(pagination.getWhere(), pagination.getPage());
        }else{
            page = repository.findAll(pagination.getPage());
        }
        return new ResponseList<>(page.getContent(), page.getNumber() + 1, page.getSize(), page.getTotalPages(), page.getTotalElements());
    }
}
