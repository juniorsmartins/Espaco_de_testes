package com.devvadercursos.interface_adapters.controllers;

import com.devvadercursos.application_business.usecases.dtos.GenericsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;

public abstract class CursosController<T extends GenericsDTO<ID>, ID> {

    @PostMapping
    abstract ResponseEntity<T> cadastrar(@RequestBody @Valid T dto);

    @GetMapping
    abstract ResponseEntity<?> buscarTodos(Pageable paginacao, @RequestParam(required = false) T filtro);

    @PutMapping
    abstract ResponseEntity<T> atualizarTotalOuSalvar(@PathVariable ID id, @RequestBody @Valid T dto);

    @PatchMapping
    abstract ResponseEntity<T> atualizarParcialOuLancarExcecao(@PathVariable ID id, @RequestBody @Valid T dto);

    @DeleteMapping
    abstract void deletar(@PathVariable ID id);
}
