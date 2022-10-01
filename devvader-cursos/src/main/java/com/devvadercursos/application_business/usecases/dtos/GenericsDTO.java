package com.devvadercursos.application_business.usecases.dtos;

public sealed interface GenericsDTO<ID> permits CursoDTO, InstituicaoDTO, RegistroDTO {

    ID getId();
    void setId(ID id);
}
