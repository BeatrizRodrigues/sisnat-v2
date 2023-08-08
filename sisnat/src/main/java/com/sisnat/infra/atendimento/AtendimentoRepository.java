package com.sisnat.infra.atendimento;

import com.sisnat.domain.atendimento.Atendimento;
import com.sisnat.domain.atendimento.infra.IAtendimentoRepository;
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
public class AtendimentoRepository implements IAtendimentoRepository {

    private final IAtendimentoJPARepository repository;

    @Override
    public Atendimento buscarPorId(Long id) {
        log.debug("c=AtendimentoRepository, m=buscarPorId, id={}", id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public Atendimento deletarPorID(Atendimento atendimento) {
        log.debug("c=AtendimentoRepository, m=deletarPorID, atendimento={}", atendimento);
        repository.delete(atendimento);
        return atendimento;
    }

    @Override
    public Atendimento salvarAtendimento(Atendimento atendimento) {
        log.debug("c=AtendimentoRepository, m=salvarAtendimento, atendimento={}", atendimento);
        return repository.save(atendimento);
    }

    @Override
    public Atendimento atualizarAtendimento(Atendimento atendimento) {
        log.debug("c=AtendimentoRepository, m=atualizarAtendimento, atendimento={}", atendimento);
        return repository.save(atendimento);
    }

    @Override
    public ResponseList<Atendimento> buscarTodosAtendimento(PaginationRequest<Atendimento> pagination) {
        log.debug("c=AtendimentoRepository, m=buscarTodosAtendimento, pagination={}", pagination);
        final Page<Atendimento> page;

        if(nonNull(pagination.getWhere())){
            page = repository.findAll(pagination.getWhere(), pagination.getPage());
        }else{
            page = repository.findAll(pagination.getPage());
        }
        return new ResponseList<>(page.getContent(), page.getNumber() + 1, page.getSize(), page.getTotalPages(), page.getTotalElements());
    }
}
