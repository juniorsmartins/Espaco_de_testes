package io.crudcursos.domain.excecoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public final class TratamentoDeExcecoes {

    @Autowired
    private MessageSource mensagemInternacionalizada;

    public ResponseEntity<List<ErrorsBeanValidation>> excecaoMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<ErrorsBeanValidation> listaDeErrosTratadosDeBeanValidation = new ArrayList<>();
        List<FieldError> listaDeFieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
        listaDeFieldErrors.forEach(erro ->
        {
            String mensagem = mensagemInternacionalizada.getMessage(erro, LocaleContextHolder.getLocale());
            ErrorsBeanValidation erroTratadoParaRetorno = new ErrorsBeanValidation(HttpStatus.BAD_REQUEST.toString(),
                    mensagem, erro.getField(), erro.getCode());
            listaDeErrosTratadosDeBeanValidation.add(erroTratadoParaRetorno);
        });
        return ResponseEntity
                .badRequest()
                .body(listaDeErrosTratadosDeBeanValidation);
    }
}
