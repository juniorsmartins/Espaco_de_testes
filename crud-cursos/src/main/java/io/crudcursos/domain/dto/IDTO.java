package io.crudcursos.domain.dto;

public sealed interface IDTO<ID> permits AssuntoDTO, CursoDTO {

    ID getId();
    void setId(ID id);
}
