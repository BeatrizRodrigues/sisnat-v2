package com.sisnat.util.message;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Getter
@Component
@RequestScope
public class MessageStack {

    private Message mensagem;

    public void addMensage(final String message) {
        this.mensagem = new Message(message);
    }

}
