package com.sisnat.infra.morfometriaMammalia;

import com.sisnat.domain.morfometriaMammalia.MorfometriaMammalia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMorfometriaMammaliaJPARepository extends JpaRepository<MorfometriaMammalia, Long> {
}
