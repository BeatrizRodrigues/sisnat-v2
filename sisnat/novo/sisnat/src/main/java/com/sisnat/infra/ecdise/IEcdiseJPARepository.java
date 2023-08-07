package com.sisnat.infra.ecdise;

import com.sisnat.domain.ecdise.Ecdise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEcdiseJPARepository extends JpaRepository<Ecdise, Long> {
}
