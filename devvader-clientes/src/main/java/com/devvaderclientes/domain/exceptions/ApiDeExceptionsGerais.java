package com.devvaderclientes.domain.exceptions;

public final class ApiDeExceptionsGerais {

    private String mensagem;
    private String status;

    public ApiDeExceptionsGerais(String mensagem, String status) {
        this.mensagem = mensagem;
        this.status = status;
    }
}
