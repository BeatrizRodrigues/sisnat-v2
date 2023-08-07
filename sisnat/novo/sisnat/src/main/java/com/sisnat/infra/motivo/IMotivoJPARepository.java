package com.sisnat.infra.motivo;

import com.sisnat.domain.motivo.Motivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMotivoJPARepository extends JpaRepository<Motivo, Long> {
}
