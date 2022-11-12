package io.crudcursos.cursos_contexto.dominio.entity;

public sealed interface IEntity<ID> permits AssuntoEntity, CursoEntity{

    ID getId();
    void setId(ID id);
}
