package io.crudcursos.domain.controller;

import io.crudcursos.domain.dto.IDTO;
import io.crudcursos.domain.entity.filtros.IFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public sealed abstract class AController<D extends IDTO<ID>, F extends IFiltro, ID> permits AssuntoController, CursoController{

    @PostMapping
    abstract ResponseEntity<D> criar(D dto);

    @GetMapping
    abstract ResponseEntity<Page<D>> buscarTodos(F filtro, Pageable pageable);

    @GetMapping(path = "/{id}")
    abstract ResponseEntity<D> consultarPorId(ID id);

    @PutMapping(path = "/{id}")
    abstract ResponseEntity<D> atualizar(ID id, D dto);

    @DeleteMapping(path = "/{id}")
    abstract ResponseEntity<?> deletarPorId(ID id);
}
