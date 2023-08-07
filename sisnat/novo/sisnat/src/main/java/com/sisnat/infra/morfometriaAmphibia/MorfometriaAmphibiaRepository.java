package com.sisnat.infra.morfometriaAmphibia;

import com.sisnat.domain.morfometriaAmphibia.MorfometriaAmphibia;
import com.sisnat.domain.morfometriaAmphibia.infra.IMorfometriaAmphibiaRepository;
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
public class MorfometriaAmphibiaRepository implements IMorfometriaAmphibiaRepository {

    private final IMorfometriaAmphibiaJPARepository repository;

    @Override
    public MorfometriaAmphibia buscarPorId(Long id) {
        log.debug("c=MorfometriaAmphibiaRepository, m=buscarPorId, id={}", id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public MorfometriaAmphibia deletarPorID(MorfometriaAmphibia morfometriaAmphibia) {
        log.debug("c=MorfometriaAmphibiaRepository, m=deletarPorID, morfometriaAmphibia={}", morfometriaAmphibia);
        repository.delete(morfometriaAmphibia);
        return morfometriaAmphibia;
    }

    @Override
    public MorfometriaAmphibia salvarMorfometriaAmphibia(MorfometriaAmphibia morfometriaAmphibia) {
        log.debug("c=MorfometriaAmphibiaRepository, m=salvarMorfometriaAmphibia, morfometriaAmphibia={}", morfometriaAmphibia);
        return repository.save(morfometriaAmphibia);
    }

    @Override
    public MorfometriaAmphibia atualizarMorfometriaAmphibia(MorfometriaAmphibia morfometriaAmphibia) {
        log.debug("c=MorfometriaAmphibiaRepository, m=atualizarMorfometriaAmphibia, morfometriaAmphibia={}", morfometriaAmphibia);
        return repository.save(morfometriaAmphibia);
    }

    @Override
    public ResponseList<MorfometriaAmphibia> buscarTodosMorfometriaAmphibia(PaginationRequest<MorfometriaAmphibia> pagination) {
        log.debug("c=MorfometriaAmphibiaRepository, m=buscarTodosMorfometriaAmphibia, pagination={}", pagination);
        final Page<MorfometriaAmphibia> page;

        if(nonNull(pagination.getWhere())){
            page = repository.findAll(pagination.getWhere(), pagination.getPage());
        }else{
            page = repository.findAll(pagination.getPage());
        }
        return new ResponseList<>(page.getContent(), page.getNumber() + 1, page.getSize(), page.getTotalPages(), page.getTotalElements());
    }
}
