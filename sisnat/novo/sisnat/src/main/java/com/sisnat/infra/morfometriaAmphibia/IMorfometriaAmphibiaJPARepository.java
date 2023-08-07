package com.sisnat.infra.morfometriaAmphibia;

import com.sisnat.domain.morfometriaAmphibia.MorfometriaAmphibia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMorfometriaAmphibiaJPARepository extends JpaRepository<MorfometriaAmphibia, Long> {
}
