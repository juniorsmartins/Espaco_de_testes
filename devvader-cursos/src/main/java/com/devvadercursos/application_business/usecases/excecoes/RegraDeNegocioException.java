package com.devvadercursos.application_business.usecases.excecoes;

public final class RegraDeNegocioException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public RegraDeNegocioException(String mensagem) {
        super(mensagem);
    }
}
