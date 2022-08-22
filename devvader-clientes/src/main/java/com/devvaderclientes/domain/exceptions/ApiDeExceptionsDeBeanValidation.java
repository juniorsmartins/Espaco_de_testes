package com.devvaderclientes.domain.exceptions;

import lombok.Getter;

@Getter
public final class ApiDeExceptionsDeBeanValidation {

    private String status;
    private String mensagem;
    private String anotacao;
    private String nomeDoCampo;

    public ApiDeExceptionsDeBeanValidation(String status, String mensagem) {
        this.status = status;
        this.mensagem = mensagem;
    }

    public ApiDeExceptionsDeBeanValidation(String status, String mensagem, String nomeDoCampo, String anotacao) {
        this(status, mensagem);
        this.nomeDoCampo = nomeDoCampo;
        this.anotacao = anotacao;
    }
}
