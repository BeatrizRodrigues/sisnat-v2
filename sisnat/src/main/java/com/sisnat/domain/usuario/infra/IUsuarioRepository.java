package com.sisnat.domain.usuario.infra;

import com.sisnat.domain.usuario.Usuario;
import com.sisnat.util.pagination.PaginationRequest;
import com.sisnat.util.pagination.ResponseList;

public interface IUsuarioRepository {

    Usuario buscarPorId(final Long id);

    Usuario buscarPorUsername(final String username);

    Usuario deletarPorID(final Usuario usuario);

    Usuario salvarUsuario(final Usuario usuario);

    Usuario atualizarUsuario(final Usuario usuario);

    ResponseList<Usuario> buscarTodosUsuario(final PaginationRequest<Usuario> pagination);
}
