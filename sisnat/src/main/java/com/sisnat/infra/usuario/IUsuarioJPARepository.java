package com.sisnat.infra.usuario;

import com.sisnat.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioJPARepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsername(String username);

}
