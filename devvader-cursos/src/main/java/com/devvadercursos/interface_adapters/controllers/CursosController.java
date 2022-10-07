package com.devvadercursos.interface_adapters.controllers;

import com.devvadercursos.application_business.usecases.dtos.CursoAtualizarDTO;
import com.devvadercursos.application_business.usecases.dtos.IGenericsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract sealed class CursosController<T extends IGenericsDTO<ID>, F extends IGenericsDTO<ID>, A extends IGenericsDTO<ID>, ID> permits CursosControllerImpl {

    @PostMapping
    abstract ResponseEntity<T> cadastrar(T dto);

    @GetMapping
    abstract ResponseEntity<Page<T>> buscarTodos(Pageable paginacao, F filtro);

    @GetMapping(path = "/{id}", produces = {"application/json"})
    abstract ResponseEntity<T> consultarPorId(Long id);

    @GetMapping(value = "/porta")
    abstract String consultarPorta(String porta);

    @PutMapping(value = "/{id}")
    abstract ResponseEntity<T> atualizarTotalOuSalvar(ID id, T dto);

    @PatchMapping(value = "/{id}")
    abstract ResponseEntity<T> atualizarParcialOuLancarExcecao(ID id, A dto);

    @DeleteMapping(value = "/{id}")
    abstract ResponseEntity<?> deletarPorId(ID id);
}
