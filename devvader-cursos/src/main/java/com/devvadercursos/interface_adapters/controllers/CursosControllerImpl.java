package com.devvadercursos.interface_adapters.controllers;

import com.devvadercursos.application_business.usecases.dtos.response.CursoDTOOut;
import com.devvadercursos.enterprise_business.entities.Curso;
import com.devvadercursos.application_business.usecases.services.CursosService;
import com.devvadercursos.application_business.usecases.dtos.request.CursoDTOIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;

@RestController
@RequestMapping(value = "/v1/cursos")
public class CursosControllerImpl extends CursosController<CursoDTOIn, CursoDTOOut, Long> {

    @Autowired
    private CursosService cursosService;

    @Override
    public ResponseEntity<CursoDTOOut> cadastrar(CursoDTOIn dtoIn) {
        return null;
    }

    @Override
    public ResponseEntity<?> buscarTodos(Pageable paginacao, CursoDTOIn filtro) {
        return null;
    }

    @Override
    public ResponseEntity<CursoDTOOut> atualizarTotalOuSalvar(Long id, CursoDTOIn dtoIn) {
        return null;
    }

    @Override
    ResponseEntity<CursoDTOOut> atualizarParcialOuLancarExcecao(Long aLong, CursoDTOIn dtoIn) {
        return null;
    }

    @Override
    public void deletar(Long id) {

    }
}
