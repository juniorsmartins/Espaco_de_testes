package io.crudcursos.domain.service;

import io.crudcursos.domain.dto.IDTO;
import io.crudcursos.domain.entity.IEntity;
import io.crudcursos.domain.entity.filtros.IFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public sealed abstract class AService<D extends IDTO<ID>, E extends IEntity<ID>, F extends IFiltro, ID> permits AssuntoService, CursoService{

    public abstract ResponseEntity<D> criar(D dto);
    public abstract ResponseEntity<Page<D>> buscarTodos(Pageable paginacao, F filtro);
    public abstract ResponseEntity<D> consultarPorId(ID id);
    public abstract ResponseEntity<D> atualizar(ID id, D dto);
    public abstract ResponseEntity<?> deletarPorId(ID id);
}
