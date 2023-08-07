package com.sisnat.util.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message {

    private Tipo tipo;
    private String titulo;
    private String mensagem;

    public Message() {

    }

    public Message(String titulo) {
        this.tipo = Tipo.SUCESSO;
        this.titulo = titulo;
    }

    public Message(Tipo tipo, String titulo, String mensagem) {
        this.tipo = tipo;
        this.titulo = titulo;
        this.mensagem = mensagem;
    }

}
