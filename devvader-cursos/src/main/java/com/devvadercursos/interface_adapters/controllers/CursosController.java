package com.devvadercursos.interface_adapters.controllers;

import com.devvadercursos.application_business.usecases.dtos.GenericsDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;

public abstract sealed class CursosController<T extends GenericsDTO<ID>, ID> permits CursosControllerImpl {

    @PostMapping
    abstract ResponseEntity<T> cadastrar(@RequestBody @Valid T dto);

    @GetMapping
    abstract ResponseEntity<?> buscarTodos(Pageable paginacao, @RequestParam(required = false) T filtro);

    @GetMapping(value = "/porta")
    abstract String consultarPorta(@Value("${local.server.port}") String porta);

    @PutMapping(value = "/{id}")
    abstract ResponseEntity<T> atualizarTotalOuSalvar(@PathVariable(value = "id") ID id, @RequestBody @Valid T dto);

    @PatchMapping(value = "/{id}")
    abstract ResponseEntity<T> atualizarParcialOuLancarExcecao(@PathVariable(value = "id") ID id, @RequestBody @Valid T dto);

    @DeleteMapping(value = "/{id}")
    abstract ResponseEntity<?> deletar(@PathVariable(value = "id") ID id);
}
