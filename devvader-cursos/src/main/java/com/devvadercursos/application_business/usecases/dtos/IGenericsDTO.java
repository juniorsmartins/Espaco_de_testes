package com.devvadercursos.application_business.usecases.dtos;

public sealed interface IGenericsDTO<ID> permits CursoDTOI, InstituicaoDTOI, RegistroDTOI, FiltroBuscarTodos {

    ID getId();
    void setId(ID id);
}
