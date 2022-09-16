package com.devvadercursos.usecases.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public final class ValidacaoDTOIn {

    private Long id;
    private String linkCertificado;
    private String registroCertificado;
    private Long cursoId;
}
