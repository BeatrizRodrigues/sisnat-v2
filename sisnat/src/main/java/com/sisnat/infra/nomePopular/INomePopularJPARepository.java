package com.sisnat.infra.nomePopular;

import com.sisnat.domain.nomePopular.NomePopular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INomePopularJPARepository extends JpaRepository<NomePopular, Long> {
}
