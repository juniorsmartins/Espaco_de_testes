package com.devvaderclientes.domain.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public final class ControleDeExceptions {

    @Autowired
    private MessageSource mensagemInternacionalizada;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> excecaoMethodNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {

        List<ApiDeExceptionsDeBeanValidation> errosDeValidacao = new ArrayList<>();
        List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();

        fieldErrors.forEach(erro -> {
            String mensagem = mensagemInternacionalizada.getMessage(erro, LocaleContextHolder.getLocale());
            ApiDeExceptionsDeBeanValidation erroPersonalizadoParaRetorno = new ApiDeExceptionsDeBeanValidation(
                    HttpStatus.BAD_REQUEST.toString(), mensagem, erro.getField(), erro.getCode());
            errosDeValidacao.add(erroPersonalizadoParaRetorno);
        });
        return ResponseEntity.badRequest().body(errosDeValidacao);
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiDeExceptionsGerais excecaoRecursoNaoEncontradoException(RecursoNaoEncontradoException recursoNaoEncontradoException) {
        return new ApiDeExceptionsGerais(recursoNaoEncontradoException.getMessage(), HttpStatus.NOT_FOUND.toString());
    }
}
