package com.sisnat.api.app.usuario.dto;

import com.sisnat.domain.usuario.Usuario;
import com.sisnat.util.message.MessageStack;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UsuarioConverterDTO {

    private final MessageStack messageStack;

    public UsuarioDTO converter(final Usuario usuario){
        log.debug("c=UsuarioConverterDTO, m=converter, usuario={}", usuario);

        final var dto = new UsuarioDTO();

        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setUsername(usuario.getUsername());
        dto.setPassword(usuario.getPassword());

        dto.setMensagem(messageStack.getMensagem());

        return dto;
    }
}
