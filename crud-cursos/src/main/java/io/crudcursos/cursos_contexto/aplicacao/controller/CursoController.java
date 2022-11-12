package io.crudcursos.cursos_contexto.aplicacao.controller;

import io.crudcursos.cursos_contexto.dominio.dto.CursoDTORequest;
import io.crudcursos.cursos_contexto.dominio.dto.CursoDTOResponse;
import io.crudcursos.cursos_contexto.dominio.entity.CursoEntity;
import io.crudcursos.cursos_contexto.dominio.filtros.CursoFiltro;
import io.crudcursos.cursos_contexto.dominio.service.AService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "${app.api.base}/cursos", produces = {"application/json"})
public final class CursoController extends AController<CursoDTORequest, CursoDTOResponse, CursoFiltro, Long> {

    @Autowired
    private AService<CursoDTORequest, CursoDTOResponse, CursoFiltro, CursoEntity, Long> service;

    @Override
    public ResponseEntity<CursoDTOResponse> criar(@RequestBody @Valid CursoDTORequest dto) {
        return this.service.criar(dto);
    }

    @Override
    public ResponseEntity<Page<CursoDTOResponse>> buscarTodos(CursoFiltro filtro,
             @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao) {
        return this.service.buscarTodos(filtro, paginacao);
    }

    @Override
    public ResponseEntity<CursoDTOResponse> consultarPorId(@PathVariable(value = "id") Long id) {
        return this.service.consultarPorId(id);
    }

    @Override
    public ResponseEntity<CursoDTOResponse> atualizar(@PathVariable(value = "id") Long id, @RequestBody @Valid CursoDTORequest dto) {
        return this.service.atualizar(id, dto);
    }

    @Override
    public ResponseEntity<?> deletarPorId(@PathVariable(value = "id") Long id) {
        return this.service.deletarPorId(id);
    }
}
