package com.devvadercursos.interface_adapters.controllers;

import com.devvadercursos.enterprise_business.entities.Entidade;
import com.devvadercursos.application_business.usecases.dtos.request.DTOIn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;

public abstract class CursosController<T extends Entidade<ID>, DTO extends DTOIn<ID>, ID> {

    @PostMapping
    abstract ResponseEntity<?> cadastrar(@RequestBody @Valid DTO dto);

    @GetMapping
    abstract ResponseEntity<?> buscarTodos(Pageable paginacao, @RequestParam(required = false) DTO filtro);

    @PutMapping
    abstract ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid DTO dto);

    @DeleteMapping
    abstract void deletar(@PathVariable Long id);
}
