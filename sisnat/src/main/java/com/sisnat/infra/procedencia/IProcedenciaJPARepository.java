package com.sisnat.infra.procedencia;

import com.sisnat.domain.procedencia.Procedencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProcedenciaJPARepository extends JpaRepository<Procedencia, Long> {
}
