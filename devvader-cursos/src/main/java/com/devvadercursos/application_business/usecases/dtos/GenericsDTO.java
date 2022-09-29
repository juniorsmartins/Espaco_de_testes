package com.devvadercursos.application_business.usecases.dtos;

public interface GenericsDTO<ID> {

    ID getId();
    void setId(ID id);
}
