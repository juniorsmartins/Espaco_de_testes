package com.devvadercursos.usecases.dtos.response;

import com.devvadercursos.entities.enuns.EstadoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class InstituicaoDTOOut implements DTOOut<Long> {

    private Long id;
    private String nome;
    private String sigla;
    private EstadoEnum estado;
    private Long cursoId;
}
