package com.devvadercursos.application_business.usecases.dtos.request;

import com.devvadercursos.enterprise_business.entities.Entidade;
import com.devvadercursos.enterprise_business.entities.enuns.EstadoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public final class InstituicaoDTOIn extends Entidade<Long> implements Serializable {

    private String nome;
    private Long cursoId;
}
