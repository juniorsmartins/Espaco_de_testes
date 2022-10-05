package com.devvadercursos.application_business.usecases.excecoes;

public final class InternalErrorsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InternalErrorsException(String mensagem) {
        super(mensagem);
    }

    public InternalErrorsException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
