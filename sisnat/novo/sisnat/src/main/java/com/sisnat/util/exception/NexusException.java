package com.sisnat.util.exception;

import com.sisnat.util.error.ErrorMessage;
import lombok.Getter;

@Getter
public class NexusException extends RuntimeException {

    private final ErrorMessage error;


    public NexusException(ErrorMessage error) {
        this.error = error;
    }

    public static  NexusException of(final String titulo, final ErrorMessage error){
        throw new NexusException(new ErrorMessage(titulo,error.getDetalhes()));
    }
}
