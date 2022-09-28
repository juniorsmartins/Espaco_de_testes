package com.devvadercursos.interface_adapters.controllers;

import com.devvadercursos.enterprise_business.entities.Curso;
import com.devvadercursos.application_business.usecases.CursosService;
import com.devvadercursos.application_business.usecases.dtos.request.CursoDTOIn;
import com.devvadercursos.interface_adapters.controllers.CursosController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;

@RestController
@RequestMapping(value = "/v1/cursos")
public class CursosControllerImpl extends CursosController<Curso, CursoDTOIn, Long> {

    @Autowired
    private CursosService cursosService;

    @Override
    public ResponseEntity<?> cadastrar(CursoDTOIn dtoIn) {
        return null;
    }

    @Override
    public ResponseEntity<?> buscarTodos(Pageable paginacao, CursoDTOIn filtro) {
        return null;
    }

    @Override
    public ResponseEntity<?> atualizar(Long id, CursoDTOIn dtoIn) {
        return null;
    }

    @Override
    public void deletar(Long id) {

    }
}
