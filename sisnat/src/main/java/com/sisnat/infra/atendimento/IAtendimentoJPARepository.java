package com.sisnat.infra.atendimento;

import com.sisnat.domain.atendimento.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAtendimentoJPARepository extends JpaRepository<Atendimento, Long> {
}
