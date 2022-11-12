package io.crudcursos.domain.dto;

public sealed interface IDTO<ID> permits AssuntoDTORequest, AssuntoDTOResponse, CursoDTOResponse, CursoDTORequest {}
