package com.devvadercursos.application_business.usecases.excecoes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public final class ApiDeErroQualquer {

    private String status;
    private String mensagem;
}
