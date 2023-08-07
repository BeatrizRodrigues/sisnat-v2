package com.sisnat.infra.procedencia;

import com.sisnat.domain.procedencia.Procedencia;
import com.sisnat.domain.procedencia.infra.IProcedenciaRepository;
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
public class ProcedenciaRepository implements IProcedenciaRepository {

    private final IProcedenciaJPARepository repository;

    @Override
    public Procedencia buscarPorId(Long id) {
        log.debug("c=ProcedenciaRepository, m=buscarPorId, id={}", id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public Procedencia deletarPorID(Procedencia procedencia) {
        log.debug("c=ProcedenciaRepository, m=deletarPorID, procedencia={}", procedencia);
        repository.delete(procedencia);
        return procedencia;
    }

    @Override
    public Procedencia salvarProcedencia(Procedencia procedencia) {
        log.debug("c=ProcedenciaRepository, m=salvarProcedencia, procedencia={}", procedencia);
        return repository.save(procedencia);
    }

    @Override
    public Procedencia atualizarProcedencia(Procedencia procedencia) {
        log.debug("c=ProcedenciaRepository, m=atualizarProcedencia, Procedencia={}", procedencia);
        return repository.save(procedencia);
    }

    @Override
    public ResponseList<Procedencia> buscarTodosProcedencia(PaginationRequest<Procedencia> pagination) {
        log.debug("c=ProcedenciaRepository, m=buscarTodosProcedencia, pagination={}", pagination);
        final Page<Procedencia> page;

        if(nonNull(pagination.getWhere())){
            page = repository.findAll(pagination.getWhere(), pagination.getPage());
        }else{
            page = repository.findAll(pagination.getPage());
        }
        return new ResponseList<>(page.getContent(), page.getNumber() + 1, page.getSize(), page.getTotalPages(), page.getTotalElements());
    }
}
