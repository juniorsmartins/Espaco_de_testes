package com.devvadercursos.enterprise_business.entities;

public sealed interface IGenericsEntity<ID> permits Curso, Instituicao, Registro {

    ID getId();
    void setId(ID id);
}
