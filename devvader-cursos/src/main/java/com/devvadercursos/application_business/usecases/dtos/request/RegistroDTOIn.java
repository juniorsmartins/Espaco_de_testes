package com.devvadercursos.application_business.usecases.dtos.request;

import com.devvadercursos.enterprise_business.entities.Entidade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public final class RegistroDTOIn extends Entidade<Long> implements Serializable {

    private Long id;
    private String linkCertificado;
    private String numeroCertificado;
    private Long cursoId;
}
