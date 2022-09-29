package com.devvadercursos.enterprise_business.entities;

public interface GenericsEntity<ID> {

    ID getId();
    void setId(ID id);
}
