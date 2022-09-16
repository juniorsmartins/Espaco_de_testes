package com.devvadercursos.usecases.dtos.request;

import com.devvadercursos.entities.enuns.EstadoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public final class InstituicaoDTOIn {

    private String nome;
    private String sigla;
    private EstadoEnum estado;
    private Long cursoId;
}
