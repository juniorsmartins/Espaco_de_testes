package com.devvadercursos.interface_adapters.controllers;

import com.devvadercursos.application_business.usecases.dtos.GenericsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

public abstract sealed class CursosController<T extends GenericsDTO<ID>, ID> permits CursosControllerImpl {

    @PostMapping
    abstract ResponseEntity<T> cadastrar(T dto);

    @GetMapping
    abstract ResponseEntity<?> buscarTodos(Pageable paginacao, T filtro);

    @GetMapping(value = "/{id}")
    abstract ResponseEntity<T> consultarPorId(Long id);

    @GetMapping(value = "/porta")
    abstract String consultarPorta(String porta);

    @PutMapping(value = "/{id}")
    abstract ResponseEntity<T> atualizarTotalOuSalvar(ID id, T dto);

    @PatchMapping(value = "/{id}")
    abstract ResponseEntity<T> atualizarParcialOuLancarExcecao(ID id, T dto);

    @DeleteMapping(value = "/{id}")
    abstract ResponseEntity<?> deletarPorId(ID id);
}
