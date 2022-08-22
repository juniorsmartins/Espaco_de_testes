package com.devvaderclientes.domain.exceptions;

import lombok.Getter;

@Getter
public final class ApiDeErrorsDeBeanValidation {

    private String status;
    private String mensagem;
    private String anotacao;
    private String nomeDoCampo;

    public ApiDeErrorsDeBeanValidation(String status, String mensagem) {
        this.status = status;
        this.mensagem = mensagem;
    }

    public ApiDeErrorsDeBeanValidation(String status, String mensagem, String nomeDoCampo, String anotacao) {
        this(status, mensagem);
        this.nomeDoCampo = nomeDoCampo;
        this.anotacao = anotacao;
    }
}
