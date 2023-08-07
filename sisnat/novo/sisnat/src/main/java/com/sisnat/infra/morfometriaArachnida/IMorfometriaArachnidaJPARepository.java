package com.sisnat.infra.morfometriaArachnida;

import com.sisnat.domain.morfometriaArachnida.MorfometriaArachnida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMorfometriaArachnidaJPARepository extends JpaRepository<MorfometriaArachnida, Long> {
}
