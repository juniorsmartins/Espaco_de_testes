package io.crudcursos.domain.service;

import io.crudcursos.domain.dto.IDTO;
import io.crudcursos.domain.entity.IEntity;
import io.crudcursos.domain.entity.filtros.IFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public sealed interface IService<D extends IDTO<ID>, E extends IEntity<ID>, F extends IFiltro, ID> permits AssuntoService, CursoService{

    ResponseEntity<D> cadastrar(D dto);
    ResponseEntity<Page<D>> buscarTodos(Pageable paginacao, F filtro);
    ResponseEntity<D> consultarPorId(ID id);
    ResponseEntity<D> atualizar(ID id, D dto);
    ResponseEntity<?> deletarPorId(ID id);
}
