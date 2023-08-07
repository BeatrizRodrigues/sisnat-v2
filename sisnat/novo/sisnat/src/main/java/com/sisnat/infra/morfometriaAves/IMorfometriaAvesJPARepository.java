package com.sisnat.infra.morfometriaAves;

import com.sisnat.domain.morfometriaAves.MorfometriaAves;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMorfometriaAvesJPARepository extends JpaRepository<MorfometriaAves, Long> {
}
