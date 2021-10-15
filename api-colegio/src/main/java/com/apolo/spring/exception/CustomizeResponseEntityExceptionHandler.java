package com.apolo.spring.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.util.Arrays;
import java.util.Date;
import java.util.List;

@ControllerAdvice
@RestController
public class CustomizeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(EmptyResultDataAccessException.class)
    public final ResponseEntity<Object> EmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request){
        //se hace un parse de la respuesta para no mostrar el mensaje en ingles
        //se hace acá para no hacer una validación extra en todos los deletes

        String [] message  = ex.getMessage().split(" ");
        String[] clase = message[2].split("\\.");
        String nuevoMensaje = String.format("No existe %s con el id %s", clase[clase.length-1], message[6]);
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), nuevoMensaje, request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ErrorGeneralExcepcion.class)
    public final ResponseEntity<Object> handleErrorGeneralExcepcion(ErrorGeneralExcepcion ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ObjetoNoEncontradoException.class)
    public final ResponseEntity<Object> handleObjetoNoEncontrado(ObjetoNoEncontradoException ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ObjetoExistenteException.class)
    public final ResponseEntity<Object> handleObjetoExistenteException(ObjetoExistenteException ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({org.springframework.security.core.AuthenticationException.class})
    public final ResponseEntity<Object> handleAuthenticationException(org.springframework.security.core.AuthenticationException ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        StringBuilder res = new StringBuilder();
        for (FieldError fieldError: fieldErrors) {
            res.append(String.format("campo: %s , regla: %s\n",fieldError.getField(), fieldError.getDefaultMessage()));
        }
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation failed", res.toString());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
