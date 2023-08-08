package com.sisnat.infra.diario;

import com.sisnat.domain.diario.Diario;
import com.sisnat.domain.diario.infra.IDiarioRepository;
import com.sisnat.domain.morfometriaReptilia.MorfometriaReptilia;
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
public class DiarioRepository implements IDiarioRepository {

    private final IDiarioJPARepository repository;

    @Override
    public Diario buscarPorId(Long id) {
        log.debug("c=DiarioRepository, m=buscarPorID, id={}", id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public Diario deletarPorID(Diario diario) {
        log.debug("c=DiarioRepository, m=deletarPorID, diario={}", diario);
        repository.delete(diario);
        return diario;
    }

    @Override
    public Diario salvarDiario(Diario diario) {
        log.debug("c=DiarioRepository, m=salvarDiario, diario={}", diario);
        return repository.save(diario);
    }

    @Override
    public Diario atualizarDiario(Diario diario) {
        log.debug("c=DiarioRepository, m=atualizarDiario, diario={}", diario);
        return repository.save(diario);
    }

    @Override
    public ResponseList<Diario> buscarTodosDiario(PaginationRequest<Diario> pagination) {
        log.debug("c=DiarioRepository, m=buscarTodosDiario, pagination={}", pagination);
        final Page<Diario> page;

        if(nonNull(pagination.getWhere())){
            page = repository.findAll(pagination.getWhere(), pagination.getPage());
        }else{
            page = repository.findAll(pagination.getPage());
        }
        return new ResponseList<>(page.getContent(), page.getNumber() + 1, page.getSize(), page.getTotalPages(), page.getTotalElements());
    }
}
