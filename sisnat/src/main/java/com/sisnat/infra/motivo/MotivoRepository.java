package com.sisnat.infra.motivo;

import com.sisnat.domain.motivo.Motivo;
import com.sisnat.domain.motivo.infra.IMotivoRepository;
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
public class MotivoRepository implements IMotivoRepository {

    private final IMotivoJPARepository repository;

    @Override
    public Motivo buscarPorId(Long id) {
        log.debug("c=MotivoRepository, m=buscarPorId, id={}", id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public Motivo deletarPorID(Motivo motivo) {
        log.debug("c=MotivoRepository, m=deletarPorID, motivo={}", motivo);
        repository.delete(motivo);
        return motivo;
    }

    @Override
    public Motivo salvarMotivo(Motivo motivo) {
        log.debug("c=MotivoRepository, m=salvarMotivo, motivo={}", motivo);
        return repository.save(motivo);
    }

    @Override
    public Motivo atualizarMotivo(Motivo motivo) {
        log.debug("c=MotivoRepository, m=atualizarMotivo, motivo={}", motivo);
        return repository.save(motivo);
    }

    @Override
    public ResponseList<Motivo> buscarTodosMotivo(PaginationRequest<Motivo> pagination) {
        log.debug("c=MotivoRepository, m=buscarTodosMotivo, pagination={}", pagination);
        final Page<Motivo> page;

        if(nonNull(pagination.getWhere())){
            page = repository.findAll(pagination.getWhere(), pagination.getPage());
        }else{
            page = repository.findAll(pagination.getPage());
        }
        return new ResponseList<>(page.getContent(), page.getNumber() + 1, page.getSize(), page.getTotalPages(), page.getTotalElements());
    }
}
