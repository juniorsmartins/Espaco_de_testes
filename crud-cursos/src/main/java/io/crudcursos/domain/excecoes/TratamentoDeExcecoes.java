package io.crudcursos.domain.excecoes;

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
public final class TratamentoDeExcecoes {

    @Autowired
    private MessageSource mensagemInternacionalizada;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExcecoesDeBeanValidationTratadas> excecaoMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<ExcecoesDeBeanValidationTratadas> listaDeErrosTratadosDeBeanValidation = new ArrayList<>();
        List<FieldError> listaDeFieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
        listaDeFieldErrors.forEach(erro ->
        {
            String mensagem = mensagemInternacionalizada.getMessage(erro, LocaleContextHolder.getLocale());
            ExcecoesDeBeanValidationTratadas erroTratadoParaRetorno = new ExcecoesDeBeanValidationTratadas(HttpStatus.BAD_REQUEST.toString(),
                    mensagem, erro.getField(), erro.getCode());
            listaDeErrosTratadosDeBeanValidation.add(erroTratadoParaRetorno);
        });
        return ResponseEntity
                .badRequest()
                .body(listaDeErrosTratadosDeBeanValidation.get(0));
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExcecoesGeraisTratadas> excecaoRecursoNaoEncontradoException(RecursoNaoEncontradoException recursoNaoEncontradoException) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ExcecoesGeraisTratadas(HttpStatus.NOT_FOUND.toString(), recursoNaoEncontradoException.getMessage()));
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExcecoesGeraisTratadas> excecaoNullPointerException(NullPointerException nullPointerException) {
        return ResponseEntity
                .badRequest()
                .body(new ExcecoesGeraisTratadas(HttpStatus.BAD_GATEWAY.toString(), nullPointerException.getMessage()));
    }

    @ExceptionHandler(RegrasDeNegocioException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ExcecoesGeraisTratadas> excecaoRegrasDeNegocioException(RegrasDeNegocioException regrasDeNegocioException) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ExcecoesGeraisTratadas(HttpStatus.CONFLICT.toString(), regrasDeNegocioException.getMessage()));
    }
}
