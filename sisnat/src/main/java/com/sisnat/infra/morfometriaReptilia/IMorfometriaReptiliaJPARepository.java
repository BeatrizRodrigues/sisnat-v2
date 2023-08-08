package com.sisnat.infra.morfometriaReptilia;

import com.sisnat.domain.morfometriaReptilia.MorfometriaReptilia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMorfometriaReptiliaJPARepository extends JpaRepository<MorfometriaReptilia, Long> {
}
