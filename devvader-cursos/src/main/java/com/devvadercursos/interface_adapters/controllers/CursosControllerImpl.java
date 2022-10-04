package com.devvadercursos.interface_adapters.controllers;

import com.devvadercursos.application_business.usecases.dtos.CursoDTOI;
import com.devvadercursos.application_business.usecases.dtos.FiltroBuscarTodos;
import com.devvadercursos.application_business.usecases.services.IGenericsService;
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
public final class CursosControllerImpl extends CursosController<CursoDTOI, FiltroBuscarTodos, Long> {

    @Autowired
    private IGenericsService<CursoDTOI, FiltroBuscarTodos, Curso, Long> cursoService;

    @Override
    public ResponseEntity<CursoDTOI> cadastrar(@RequestBody @Valid CursoDTOI dtoIn) {
        return cursoService.cadastrar(dtoIn);
    }

    @Override
    public ResponseEntity<Page<CursoDTOI>> buscarTodos(@PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0,
            size = 3) Pageable paginacao, FiltroBuscarTodos filtro) {
        return cursoService.buscarTodos(paginacao, filtro);
    }

    @Override
    public ResponseEntity<CursoDTOI> consultarPorId(@PathVariable(value = "id") Long id) {
        return cursoService.consultarPorId(id);
    }

    @Override
    public String consultarPorta(@Value("${local.server.port}") String porta) {
        return String.format("Porta: %s", porta);
    }

    @Override
    public ResponseEntity<CursoDTOI> atualizarTotalOuSalvar(@PathVariable(value = "id") Long id, @RequestBody @Valid CursoDTOI cursoDTO) {
        return cursoService.atualizarTotalOuSalvar(id, cursoDTO);
    }

    @Override
    ResponseEntity<CursoDTOI> atualizarParcialOuLancarExcecao(@PathVariable(value = "id") Long id, @RequestBody @Valid CursoDTOI cursoDTO) {
        return cursoService.atualizarParcialOuLancarExcecao(id, cursoDTO);
    }

    @Override
    public ResponseEntity<?> deletarPorId(@PathVariable(value = "id") Long id) {
        return cursoService.deletarPorId(id);
    }
}
