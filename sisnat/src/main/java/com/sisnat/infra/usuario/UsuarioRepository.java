package com.sisnat.infra.usuario;

import com.sisnat.domain.usuario.Usuario;
import com.sisnat.domain.usuario.infra.IUsuarioRepository;
import com.sisnat.util.pagination.PaginationRequest;
import com.sisnat.util.pagination.ResponseList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import static java.util.Objects.nonNull;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UsuarioRepository implements IUsuarioRepository {
    
    private final IUsuarioJPARepository repository;


    @Override
    public Usuario buscarPorId(Long id) {
        log.debug("c=UsuarioRepository, m=buscarPorId, id={}", id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public Usuario buscarPorUsername(String username) {
        log.debug("c=UsuarioRepository, m=deletarPorId, username={}", username);
        return repository.findByUsername(username);
    }

    @Override
    public Usuario deletarPorID(Usuario usuario) {
        log.debug("c=UsuarioRepository, m=deletarPorId, usuario={}", usuario);
        repository.delete(usuario);
        return usuario;
    }

    @Override
    public Usuario salvarUsuario(Usuario usuario) {
        log.debug("c=UsuarioRepository, m=salvarUsuario, usuario={}", usuario);
        return repository.save(usuario);
    }

    @Override
    public Usuario atualizarUsuario(Usuario usuario) {
        log.debug("c=UsuarioRepository, m=atualizarUsuario, usuario={}", usuario);
        return repository.save(usuario);
    }

    @Override
    public ResponseList<Usuario> buscarTodosUsuario(PaginationRequest<Usuario> pagination) {
        log.debug("c=UsuarioRepository, m=buscarTodosUsuario, pagination={}", pagination);
        final Page<Usuario> page;

        if(nonNull(pagination.getWhere())){
            page = repository.findAll(pagination.getWhere(), pagination.getPage());
        }else{
            page = repository.findAll(pagination.getPage());
        }
        return new ResponseList<>(page.getContent(), page.getNumber() + 1, page.getSize(), page.getTotalPages(), page.getTotalElements());
    }
}
