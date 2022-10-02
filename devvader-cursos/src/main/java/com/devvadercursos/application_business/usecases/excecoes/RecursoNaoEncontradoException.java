package com.devvadercursos.application_business.usecases.excecoes;

public final class RecursoNaoEncontradoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RecursoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
