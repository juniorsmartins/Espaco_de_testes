package com.devvadercursos.application_business.usecases.dtos;

public sealed interface IGenericsDTO<ID> permits CursoDTO, InstituicaoDTO, RegistroDTO, FiltroBuscarTodos, CursoAtualizarDTO {

    ID getId();
    void setId(ID id);
}
