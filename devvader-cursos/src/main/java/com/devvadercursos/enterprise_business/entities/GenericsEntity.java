package com.devvadercursos.enterprise_business.entities;

public sealed interface GenericsEntity<ID> permits Curso, Instituicao, Registro {

    ID getId();
    void setId(ID id);
}
