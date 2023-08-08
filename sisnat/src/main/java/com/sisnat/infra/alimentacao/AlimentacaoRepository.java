package com.sisnat.infra.alimentacao;

import com.sisnat.domain.alimentacao.Alimentacao;
import com.sisnat.domain.alimentacao.infra.IAlimentacaoRepository;
import com.sisnat.domain.morfometriaAmphibia.MorfometriaAmphibia;
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
public class AlimentacaoRepository implements IAlimentacaoRepository {

    private final IAlimentacaoJPARepository repository;

    @Override
    public Alimentacao buscarPorId(Long id) {
        log.debug("c=AlimentacaoRepository, m=buscarPorId, id={}", id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public Alimentacao deletarPorID(Alimentacao alimentacao) {
        log.debug("c=AlimentacaoRepository, m=deletarPorID, alimentacao={}", alimentacao);
        repository.delete(alimentacao);
        return alimentacao;
    }

    @Override
    public Alimentacao salvarAlimentacao(Alimentacao alimentacao) {
        log.debug("c=AlimentacaoRepository, m=salvarAlimentacao, alimentacao={}", alimentacao);
        return repository.save(alimentacao);
    }

    @Override
    public Alimentacao atualizarAlimentacao(Alimentacao alimentacao) {
        log.debug("c=AlimentacaoRepository, m=atualizarAlimentacao, alimentacao={}", alimentacao);
        return repository.save(alimentacao);
    }

    @Override
    public ResponseList<Alimentacao> buscarTodosAlimentacao(PaginationRequest<Alimentacao> pagination) {
        log.debug("c=AlimentacaoRepository, m=buscarTodosAlimentacao, pagination={}", pagination);
        final Page<Alimentacao> page;

        if(nonNull(pagination.getWhere())){
            page = repository.findAll(pagination.getWhere(), pagination.getPage());
        }else{
            page = repository.findAll(pagination.getPage());
        }
        return new ResponseList<>(page.getContent(), page.getNumber() + 1, page.getSize(), page.getTotalPages(), page.getTotalElements());
    }
}
