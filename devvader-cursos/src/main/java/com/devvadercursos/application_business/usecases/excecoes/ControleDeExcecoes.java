package com.devvadercursos.application_business.usecases.excecoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public final class ControleDeExcecoes {

    @Autowired
    private MessageSource mensagemInternacionalizada;

    @InitBinder("internalErrorsException")
    protected void initInternalErrorsExceptionBinder(WebDataBinder binder) {
        binder.setAllowedFields("detailMessage");
    }

    @ExceptionHandler(InternalErrorsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public LogErrorsDTO internalErrorsExceptionHandlers(InternalErrorsException internalErrorsException) {
        return new LogErrorsDTO(internalErrorsException);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> filtrarMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<ApiDeErrosDeBeanValidation> listaDeErrosDeValidacao = new ArrayList<>();

        List<FieldError> listaDeFieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
        listaDeFieldErrors.forEach(erro -> {
            String mensagem = mensagemInternacionalizada.getMessage(erro, LocaleContextHolder.getLocale());
            ApiDeErrosDeBeanValidation erroParaRetorno = new ApiDeErrosDeBeanValidation(
                    HttpStatus.BAD_REQUEST.toString(), mensagem, erro.getField(), erro.getCode());
            listaDeErrosDeValidacao.add(erroParaRetorno);
        });

        return ResponseEntity
                .badRequest()
                .body(listaDeErrosDeValidacao);
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiDeErroQualquer> filtrarRecursoNaoEncontradoException(RecursoNaoEncontradoException recursoNaoEncontradoException) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiDeErroQualquer(HttpStatus.NOT_FOUND.toString(), recursoNaoEncontradoException.getMessage()));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiDeErroQualquer> filtrarRegraDeNegocioException(RegraDeNegocioException regraDeNegocioException) {
        return ResponseEntity
                .badRequest()
                .body(new ApiDeErroQualquer(HttpStatus.BAD_REQUEST.toString(), regraDeNegocioException.getMessage()));
    }
}
