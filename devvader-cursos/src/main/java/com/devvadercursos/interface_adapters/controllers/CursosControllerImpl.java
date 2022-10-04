package com.devvadercursos.interface_adapters.controllers;

import com.devvadercursos.application_business.usecases.dtos.CursoPatchDTO;
import com.devvadercursos.application_business.usecases.dtos.CursoDTO;
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
public final class CursosControllerImpl extends CursosController<CursoDTO, CursoPatchDTO, FiltroBuscarTodos, Long> {

    @Autowired
    private IGenericsService<CursoDTO, CursoPatchDTO, FiltroBuscarTodos, Curso, Long> cursoService;

    @Override
    public ResponseEntity<CursoDTO> cadastrar(@RequestBody @Valid CursoDTO dtoIn) {
        return cursoService.cadastrar(dtoIn);
    }

    @Override
    public ResponseEntity<Page<CursoDTO>> buscarTodos(@PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0,
            size = 3) Pageable paginacao, FiltroBuscarTodos filtro) {
        return cursoService.buscarTodos(paginacao, filtro);
    }

    @Override
    public ResponseEntity<CursoDTO> consultarPorId(@PathVariable(value = "id") Long id) {
        return cursoService.consultarPorId(id);
    }

    @Override
    public String consultarPorta(@Value("${local.server.port}") String porta) {
        return String.format("Porta: %s", porta);
    }

    @Override
    public ResponseEntity<CursoDTO> atualizarTotalOuSalvar(@PathVariable(value = "id") Long id, @RequestBody @Valid CursoDTO cursoDTO) {
        return cursoService.atualizarTotalOuSalvar(id, cursoDTO);
    }

    @Override
    public ResponseEntity<CursoPatchDTO> atualizarParcialOuLancarExcecao(@PathVariable(value = "id") Long id, @RequestBody
        @Valid CursoPatchDTO cursoPatchDTO) {
        return cursoService.atualizarParcialOuLancarExcecao(id, cursoPatchDTO);
    }

    @Override
    public ResponseEntity<?> deletarPorId(@PathVariable(value = "id") Long id) {
        return cursoService.deletarPorId(id);
    }
}
