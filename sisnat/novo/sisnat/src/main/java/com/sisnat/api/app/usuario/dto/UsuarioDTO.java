package com.sisnat.api.app.usuario.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sisnat.util.message.Message;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private String username;
    private String password;

    private Message mensagem;
}
