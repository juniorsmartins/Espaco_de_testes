package com.devvadercursos.application_business.usecases.dtos.response;

import com.devvadercursos.enterprise_business.entities.Entidade;
import com.devvadercursos.enterprise_business.entities.enuns.EstadoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class InstituicaoDTOOut extends DTOOut<Long> implements Serializable {

    private Long id;
    private String nome;
    private Long cursoId;
}
