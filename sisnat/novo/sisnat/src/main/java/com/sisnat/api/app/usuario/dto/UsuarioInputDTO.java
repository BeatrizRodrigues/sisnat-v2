package com.sisnat.api.app.usuario.dto;

import com.sisnat.domain.usuario.Tipo;
import com.sisnat.domain.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioInputDTO {

    private String nome;
    private String email;
    private String username;
    private String password;
    private Tipo tipo;

    public Usuario toModel(){
        return Usuario
                .builder()
                .nome(this.nome)
                .email(this.email)
                .username(this.username)
                .password(this.password)
                .tipo(this.tipo)
                .build();
    }
}
