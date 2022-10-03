package com.devvadercursos.interface_adapters.controllers;

import com.devvadercursos.application_business.usecases.dtos.CursoDTO;
import com.devvadercursos.application_business.usecases.dtos.FiltroBuscarTodos;
import com.devvadercursos.application_business.usecases.services.ServiceGenerics;
import com.devvadercursos.enterprise_business.entities.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/cursos")
public final class CursosControllerImpl extends CursosController<CursoDTO, FiltroBuscarTodos, Long> {

    @Autowired
    private ServiceGenerics<CursoDTO, FiltroBuscarTodos, Curso, Long> serviceGenerics;

    @Override
    public ResponseEntity<CursoDTO> cadastrar(@RequestBody @Valid CursoDTO dtoIn) {
        return serviceGenerics.cadastrar(dtoIn);
    }

    @Override
    public ResponseEntity<Page<CursoDTO>> buscarTodos(@PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0,
            size = 3) Pageable paginacao, FiltroBuscarTodos filtro) {
        return serviceGenerics.buscarTodos(paginacao, filtro);
    }

    @Override
    public ResponseEntity<CursoDTO> consultarPorId(@PathVariable(value = "id") Long id) {
        return serviceGenerics.consultarPorId(id);
    }

    @Override
    public String consultarPorta(@Value("${local.server.port}") String porta) {
        return String.format("Porta: %s", porta);
    }

    @Override
    public ResponseEntity<CursoDTO> atualizarTotalOuSalvar(@PathVariable(value = "id") Long id, @RequestBody @Valid CursoDTO cursoDTO) {
        return serviceGenerics.atualizarTotalOuSalvar(id, cursoDTO);
    }

    @Override
    ResponseEntity<CursoDTO> atualizarParcialOuLancarExcecao(@PathVariable(value = "id") Long id, @RequestBody @Valid CursoDTO cursoDTO) {
        return serviceGenerics.atualizarParcialOuLancarExcecao(id, cursoDTO);
    }

    @Override
    public ResponseEntity<?> deletarPorId(@PathVariable(value = "id") Long id) {
        return serviceGenerics.deletarPorId(id);
    }
}
