package com.devvaderclientes.domain.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public final class EnderecoDtoRequest {

    @NotBlank
    @Length(min = 8, max = 10)
    private String cep;

    @NotBlank
    @Length(max = 100)
    private String estado;

    @NotBlank
    @Length(max = 100)
    private String cidade;

    @NotBlank
    @Length(max = 100)
    private String bairro;

    @NotBlank
    @Length(max = 150)
    private String logradouro;

    private int numero;

    @Length(max = 200)
    private String complemento;
}
