package com.apolo.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ObjetoExistenteException extends RuntimeException{

    public ObjetoExistenteException(String message) {
        super (message);
    }
}
