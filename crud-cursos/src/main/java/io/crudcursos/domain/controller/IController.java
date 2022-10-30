package io.crudcursos.domain.controller;

import io.crudcursos.domain.dto.IDTO;
import io.crudcursos.domain.entity.filtros.IFiltro;

public sealed interface IController<D extends IDTO<ID>, F extends IFiltro, ID> permits AssuntoController, CursoController{
}
