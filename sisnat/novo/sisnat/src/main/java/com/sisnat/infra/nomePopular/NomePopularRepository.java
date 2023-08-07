package com.sisnat.infra.nomePopular;

import com.sisnat.domain.nomePopular.NomePopular;
import com.sisnat.domain.nomePopular.infra.INomePopularRepository;
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
public class NomePopularRepository implements INomePopularRepository {

    private final INomePopularJPARepository repository;

    @Override
    public NomePopular buscarPorId(Long id) {
        log.debug("c=NomePopularRepository, m=buscarPorId, id={}", id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public NomePopular deletarPorID(NomePopular nomePopular) {
        log.debug("c=NomePopularRepository, m=deletarPorID, nomePopular={}", nomePopular);
        repository.delete(nomePopular);
        return nomePopular;
    }

    @Override
    public NomePopular salvarNomePopular(NomePopular nomePopular) {
        log.debug("c=NomePopularRepository, m=salvarNomePopular, nomePopular={}", nomePopular);
        return repository.save(nomePopular);
    }

    @Override
    public NomePopular atualizarNomePopular(NomePopular nomePopular) {
        log.debug("c=NomePopularRepository, m=atualizarNomePopular, nomePopular={}", nomePopular);
        return repository.save(nomePopular);
    }

    @Override
    public ResponseList<NomePopular> buscarTodosNomePopular(PaginationRequest<NomePopular> pagination) {
        log.debug("c=NomePopularRepository, m=buscarTodosNomePopular, pagination={}", pagination);
        final Page<NomePopular> page;

        if(nonNull(pagination.getWhere())){
            page = repository.findAll(pagination.getWhere(), pagination.getPage());
        }else{
            page = repository.findAll(pagination.getPage());
        }
        return new ResponseList<>(page.getContent(), page.getNumber() + 1, page.getSize(), page.getTotalPages(), page.getTotalElements());
    }
}
