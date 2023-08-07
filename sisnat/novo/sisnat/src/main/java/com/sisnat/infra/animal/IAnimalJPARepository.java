package com.sisnat.infra.animal;

import com.sisnat.domain.animal.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnimalJPARepository extends JpaRepository<Animal, Long> {
}
