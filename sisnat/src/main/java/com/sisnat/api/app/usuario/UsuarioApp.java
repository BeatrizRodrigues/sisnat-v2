package com.sisnat.api.app.usuario;

import com.sisnat.api.app.usuario.dto.UsuarioConverterDTO;
import com.sisnat.api.app.usuario.dto.UsuarioDTO;
import com.sisnat.api.app.usuario.dto.UsuarioInputDTO;
import com.sisnat.domain.usuario.Usuario;
import com.sisnat.domain.usuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioApp {

    private final UsuarioConverterDTO conversor;
    private final UsuarioService service;

    @Transactional(readOnly = true)
    public UsuarioDTO buscarPorID(final Long codigo) {
        log.debug("c=UsuarioApp, m=buscarPorID, codigo={}", codigo);

        final Usuario resultado = service.buscarPorId(codigo);

        return conversor.converter(resultado);
    }

    @Transactional(readOnly = true)
    public UsuarioDTO buscarPorUsername(final String username) {
        log.debug("c=UsuarioApp, m=buscarPorUsername, username={}", username);

        final Usuario resultado = service.buscarPorUsername(username);

        return conversor.converter(resultado);
    }

    @Transactional
    public UsuarioDTO deletarPorID(final Long id) {
        log.debug("c=UsuarioApp, m=deletarPorID, id={}", id);

        final Usuario resultado = service.deletarPorID(id);

        return conversor.converter(resultado);
    }

    @Transactional
    public UsuarioDTO salvarUsuario(final UsuarioInputDTO dto) {
        log.debug("c=UsuarioApp, m=salvarUsuario, dto={}", dto);

        final Usuario resultado = service.salvarUsuario(dto.toModel());

        return conversor.converter(resultado);
    }
}
