package com.devvadercursos.application_business.usecases.excecoes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public final class ApiDeErrosDeBeanValidation {

    private String status;
    private String mensagem;
    private String anotação;
    private String nomeCampo;
}
