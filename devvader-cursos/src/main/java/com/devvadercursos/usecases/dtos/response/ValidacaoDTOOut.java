package com.devvadercursos.usecases.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class ValidacaoDTOOut {

    private Long id;
    private String linkCertificado;
    private String registroCertificado;
    private Long cursoId;
}
