package io.crudcursos.cursos_contexto.dominio.service;

import io.crudcursos.cursos_contexto.dominio.dto.IDTO;
import io.crudcursos.cursos_contexto.dominio.entity.IEntity;
import io.crudcursos.cursos_contexto.dominio.filtros.IFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public sealed abstract class AService<R extends IDTO<ID>, S extends IDTO<ID>, F extends IFiltro, E extends IEntity<ID>, ID> permits AssuntoService, CursoService{

    public abstract ResponseEntity<S> criar(R dto);
    public abstract ResponseEntity<Page<S>> buscarTodos(F filtro, Pageable paginacao);
    public abstract ResponseEntity<S> consultarPorId(ID id);
    public abstract ResponseEntity<S> atualizar(ID id, R dto);
    public abstract ResponseEntity<?> deletarPorId(ID id);
}
