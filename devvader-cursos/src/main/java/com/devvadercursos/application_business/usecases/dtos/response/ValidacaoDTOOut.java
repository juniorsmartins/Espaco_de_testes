package com.devvadercursos.application_business.usecases.dtos.response;

import com.devvadercursos.enterprise_business.entities.Entidade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class ValidacaoDTOOut extends Entidade<Long> implements Serializable {

    private Long id;
    private String linkCertificado;
    private String numeroCertificado;
    private Long cursoId;
}
