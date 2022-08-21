package com.devvaderclientes.domain.dtos.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public final class EnderecoDtoResponse {

    private String cep;
    private String estado;
    private String cidade;
    private String bairro;
    private String logradouro;
    private int numero;
    private String complemento;
}
