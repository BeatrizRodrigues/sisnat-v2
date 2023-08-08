package com.sisnat.infra.diario;

import com.sisnat.domain.diario.Diario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDiarioJPARepository extends JpaRepository<Diario, Long> {
}
