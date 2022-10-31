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

public sealed interface IController<D extends IDTO<ID>, F extends IFiltro, ID> permits AssuntoController, CursoController{

    @PostMapping
    ResponseEntity<D> criar(D dto);

    @GetMapping
    ResponseEntity<Page<D>> buscarTodos(Pageable pageable, F filtro);

    @GetMapping(path = "/{id}", produces = {"application/json"})
    ResponseEntity<D> consultarPorId(ID id);

    @PutMapping(path = "/{id}")
    ResponseEntity<D> atualizar(ID id, D dto);

    @DeleteMapping(path = "/{id}")
    ResponseEntity<?> deletarPorId(ID id);
}
