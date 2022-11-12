package io.crudcursos.cursos_contexto.aplicacao.controller;

import io.crudcursos.cursos_contexto.dominio.dto.IDTO;
import io.crudcursos.cursos_contexto.dominio.filtros.IFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public sealed abstract class AController<R extends IDTO<ID>, S extends IDTO<ID>, F extends IFiltro, ID> permits AssuntoController, CursoController{

    @PostMapping
    abstract ResponseEntity<S> criar(R dto);

    @GetMapping
    abstract ResponseEntity<Page<S>> buscarTodos(F filtro, Pageable paginacao);

    @GetMapping(path = "/{id}")
    abstract ResponseEntity<S> consultarPorId(ID id);

    @PutMapping(path = "/{id}")
    abstract ResponseEntity<S> atualizar(ID id, R dto);

    @DeleteMapping(path = "/{id}")
    abstract ResponseEntity<?> deletarPorId(ID id);
}
