package io.crudcursos.domain.entity;

public sealed interface IEntity<ID> permits AssuntoEntity, CursoEntity{

    ID getId();
    void setId(ID id);
}
