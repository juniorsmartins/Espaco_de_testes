package io.crudcursos.domain.cursos_context.dto;

public sealed interface IDTO<ID> permits AssuntoDTORequest, AssuntoDTOResponse, CursoDTOResponse, CursoDTORequest {}
