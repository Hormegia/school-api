package com.apolo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjetoNoEncontradoException extends RuntimeException {

    public ObjetoNoEncontradoException(String message) {
        super(message);
    }
}

