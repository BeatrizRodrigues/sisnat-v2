package com.sisnat.infra.statusAnimal;

import com.sisnat.domain.statusAnimal.StatusAnimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStatusAnimalJPARepository extends JpaRepository<StatusAnimal, Long> {
}
