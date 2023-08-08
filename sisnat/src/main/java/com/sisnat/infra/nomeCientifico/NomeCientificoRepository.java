package com.sisnat.infra.nomeCientifico;

import com.sisnat.domain.nomeCientifico.NomeCientifico;
import com.sisnat.domain.nomeCientifico.infra.INomeCientificoRepository;
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
public class NomeCientificoRepository implements INomeCientificoRepository {

    private final INomeCientificoJPARepository repository;

    @Override
    public NomeCientifico buscarPorId(Long id) {
        log.debug("c=NomeCientificoRepository, m=buscarPorId, id={}", id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public NomeCientifico deletarPorID(NomeCientifico nomeCientifico) {
        log.debug("c=NomeCientificoRepository, m=deletarPorID, nomeCientifico={}", nomeCientifico);
        repository.delete(nomeCientifico);
        return nomeCientifico;
    }

    @Override
    public NomeCientifico salvarNomeCientifico(NomeCientifico nomeCientifico) {
        log.debug("c=NomeCientificoRepository, m=salvarNomeCientifico, nomeCientifico={}", nomeCientifico);
        return repository.save(nomeCientifico);
    }

    @Override
    public NomeCientifico atualizarNomeCientifico(NomeCientifico nomeCientifico) {
        log.debug("c=NomeCientificoRepository, m=atualizarNomeCientifico, nomeCientifico={}", nomeCientifico);
        return repository.save(nomeCientifico);
    }

    @Override
    public ResponseList<NomeCientifico> buscarTodosNomeCientifico(PaginationRequest<NomeCientifico> pagination) {
        log.debug("c=NomeCientificoRepository, m=buscarTodosNomeCientifico, pagination={}", pagination);
        final Page<NomeCientifico> page;

        if(nonNull(pagination.getWhere())){
            page = repository.findAll(pagination.getWhere(), pagination.getPage());
        }else{
            page = repository.findAll(pagination.getPage());
        }
        return new ResponseList<>(page.getContent(), page.getNumber() + 1, page.getSize(), page.getTotalPages(), page.getTotalElements());
    }
}
