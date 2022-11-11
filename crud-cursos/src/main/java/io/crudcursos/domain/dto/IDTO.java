package io.crudcursos.domain.dto;

public sealed interface IDTO<ID> permits AssuntoDTO, AssuntoDTOResponse, CursoDTO, CursoDTOResponse {}
