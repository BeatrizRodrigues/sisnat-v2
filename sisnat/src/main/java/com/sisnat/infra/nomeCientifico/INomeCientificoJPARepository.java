package com.sisnat.infra.nomeCientifico;

import com.sisnat.domain.nomeCientifico.NomeCientifico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INomeCientificoJPARepository extends JpaRepository<NomeCientifico, Long> {
}
