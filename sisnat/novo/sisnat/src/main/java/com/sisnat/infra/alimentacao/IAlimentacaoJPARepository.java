package com.sisnat.infra.alimentacao;

import com.sisnat.domain.alimentacao.Alimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAlimentacaoJPARepository extends JpaRepository<Alimentacao, Long> {
}
