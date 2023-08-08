package com.sisnat.util.exception;

import com.sisnat.util.error.ErrorMessage;
import lombok.Getter;

@Getter
public class NexusNotFoundException extends RuntimeException {

    private final ErrorMessage error;

    public NexusNotFoundException(final ErrorMessage error) {
        this.error = error;
    }

    public static NexusNotFoundException of(final String titulo) {
        throw new NexusNotFoundException(new ErrorMessage(titulo));
    }
}
