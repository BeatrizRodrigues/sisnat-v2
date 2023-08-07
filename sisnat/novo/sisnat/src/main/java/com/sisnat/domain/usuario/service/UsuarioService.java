package com.sisnat.domain.usuario.service;

import com.sisnat.domain.usuario.Usuario;
import com.sisnat.domain.usuario.infra.IUsuarioRepository;
import com.sisnat.util.error.ErrorMessage;
import com.sisnat.util.error.ErrorStack;
import com.sisnat.util.exception.NexusException;
import com.sisnat.util.exception.NexusNotFoundException;
import com.sisnat.util.message.MessageStack;
import com.sisnat.util.pagination.PaginationRequest;
import com.sisnat.util.pagination.ResponseList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final IUsuarioRepository repository;
    private final ErrorStack errorStack;
    private final MessageStack messageStack;

    public Usuario buscarPorId(final Long id) {
        log.debug("c=UsuarioService, m=buscarPorId, id={}", id);

        final var buscaUsuario = repository.buscarPorId(id);

        if (isNull(buscaUsuario)) {
            NexusNotFoundException.of("Usuario não encontrado.");
        }

        return buscaUsuario;
    }

    public Usuario buscarPorUsername(final String username) {
        log.debug("c=UsuarioService, m=buscarPorUsername, id={}", username);

        final var buscaUsuario = repository.buscarPorUsername(username);

        if (isNull(buscaUsuario)) {
            NexusNotFoundException.of("Usuario não encontrado.");
        }

        return buscaUsuario;
    }

    public Usuario deletarPorID(final Long id) {
        log.debug("c=UsuarioService, m=deletarPorID, id={}", id);
        final var resultado = repository.deletarPorID(this.buscarPorId(id));

        log.debug("Usuario {} excluído com sucesso", resultado);
        messageStack.addMensage("Usuario com o id " + resultado.getId() + " excluído com sucesso.");

        return resultado;
    }

    public Usuario salvarUsuario(final Usuario usuario) {
        log.debug("c=UsuarioService, m=salvarUsuario, usuario={}", usuario);
        if (!this.validar(usuario)) {
            log.error("Falha ao salvar Usuario {}", usuario);
            NexusException.of("Falha ao salvar Usuario", errorStack.getFalha());
        }

        final var usuarioSalvo = repository.salvarUsuario(usuario);
        log.debug("Usuario {} salvo com sucesso", usuarioSalvo);
        messageStack.addMensage("Usuario salvo com sucesso.");

        return usuarioSalvo;
    }

//    public Usuario atualizarUsuario(final Long id, final Usuario usuario) {
//        log.debug("c=UsuarioService, m=atualizarUsuario, id={}, usuario={}", id, usuario);
//        final var buscaUsuario = this.buscarPorId(id);
//
//        final var usuarioAtualizar = buscaUsuario
//                .toBuilder()
//                .nome(usuario.getNome())
//                .email(usuario.getNome())
//                .nome(usuario.getNome())
//                .build();
//
//
//        if (!this.validar(usuarioAtualizar)) {
//            log.error("Falha ao Atualizar Procedencia {}", procedencia);
//            NexusException.of("Falha ao Atualizar Procedencia", errorStack.getFalha());
//        }
//
//        final var ProcedenciaSalva = repository.atualizarProcedencia(procedenciaAtualizar);
//        log.debug("Procedencia {} atualizado com sucesso", ProcedenciaSalva);
//        messageStack.addMensage("Procedencia atualizado com sucesso.");
//
//        return ProcedenciaSalva;
//    }

    public ResponseList<Usuario> buscarTodosUsuario(final PaginationRequest<Usuario> pagination) {
        log.debug("c=UsuarioService, m=buscarTodosUsuario, pagination={}", pagination);

        return repository.buscarTodosUsuario(pagination);
    }

    private Boolean validar(final Usuario usuario) {
        log.debug("c=UsuarioService, m=validar, usuario={}", usuario);

        var validado = TRUE;

        if (isNull(usuario.getNome())) {
            log.error("Nome do usuario é Obrigatório [{}]", usuario);
            errorStack.addError(new ErrorMessage("Nome do usuario é obrigatório"));

            validado = FALSE;
        }

        if (isNull(usuario.getEmail())) {
            log.error("Email do usuario é Obrigatório [{}]", usuario);
            errorStack.addError(new ErrorMessage("Email do usuario é obrigatório"));

            validado = FALSE;
        }

        if (isNull(usuario.getEmail())) {
            log.error("Username do usuario é Obrigatório [{}]", usuario);
            errorStack.addError(new ErrorMessage("Username do usuario é obrigatório"));

            validado = FALSE;
        }

        if (isNull(usuario.getPassword())) {
            log.error("Password do usuario é Obrigatório [{}]", usuario);
            errorStack.addError(new ErrorMessage("Password do usuario é obrigatório"));

            validado = FALSE;
        }
        return validado;
    }
}
