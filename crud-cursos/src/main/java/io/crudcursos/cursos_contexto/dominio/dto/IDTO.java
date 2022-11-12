package io.crudcursos.cursos_contexto.dominio.dto;

public sealed interface IDTO<ID> permits AssuntoDTORequest, AssuntoDTOResponse, CursoDTOResponse, CursoDTORequest {}
