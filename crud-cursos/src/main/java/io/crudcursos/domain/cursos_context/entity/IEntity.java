package io.crudcursos.domain.cursos_context.entity;

public sealed interface IEntity<ID> permits AssuntoEntity, CursoEntity{

    ID getId();
    void setId(ID id);
}
