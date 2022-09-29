package com.devvadercursos.interface_adapters.controllers;

import com.devvadercursos.application_business.usecases.dtos.request.DTOIn;
import com.devvadercursos.application_business.usecases.dtos.response.DTOOut;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;

public abstract class CursosController<REQUEST extends DTOIn<ID>, RESPONSE extends DTOOut<ID>, ID> {

    @PostMapping
    abstract ResponseEntity<RESPONSE> cadastrar(@RequestBody @Valid REQUEST dto);

    @GetMapping
    abstract ResponseEntity<?> buscarTodos(Pageable paginacao, @RequestParam(required = false) REQUEST filtro);

    @PutMapping
    abstract ResponseEntity<RESPONSE> atualizarTotalOuSalvar(@PathVariable ID id, @RequestBody @Valid REQUEST dtoIn);

    @PatchMapping
    abstract ResponseEntity<RESPONSE> atualizarParcialOuLancarExcecao(@PathVariable ID id, @RequestBody @Valid REQUEST dtoIn);

    @DeleteMapping
    abstract void deletar(@PathVariable ID id);
}
