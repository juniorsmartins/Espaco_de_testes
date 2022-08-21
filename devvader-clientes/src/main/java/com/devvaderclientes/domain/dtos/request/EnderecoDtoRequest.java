package com.devvaderclientes.domain.dtos.request;

import lombok.Getter;

@Getter
public final class EnderecoDtoRequest {

    private String cep;
    private String estado;
    private String cidade;
    private String bairro;
    private String logradouro;
    private int numero;
    private String complemento;
}
