package com.devvadercursos.entities;

import com.devvadercursos.entities.enuns.EstadoEnum;

import java.io.Serializable;

public final class Instituicao implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String sigla;
    private EstadoEnum estado;
    private Long cursoId;
}
