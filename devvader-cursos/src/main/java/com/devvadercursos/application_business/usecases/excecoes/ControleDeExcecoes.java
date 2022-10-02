package com.devvadercursos.application_business.usecases.excecoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public final class ControleDeExcecoes {

//    @Autowired
//    private MessageSource mensagemInternacionalizada;

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiDeErroQualquer filtrarRecursoNaoEncontradoException(RecursoNaoEncontradoException recursoNaoEncontradoException) {
        return new ApiDeErroQualquer(HttpStatus.BAD_REQUEST.toString(), recursoNaoEncontradoException.getMessage());
    }
}
