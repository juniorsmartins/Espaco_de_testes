package io.crudcursos.domain.controller;

import io.crudcursos.domain.dto.CursoDTO;
import io.crudcursos.domain.entity.CursoEntity;
import io.crudcursos.domain.entity.filtros.CursoFiltro;
import io.crudcursos.domain.service.AService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "${app.api.base}/cursos", produces = {"application/json"})
public final class CursoController extends AController<CursoDTO, CursoFiltro, Long> {

    @Autowired
    private AService<CursoDTO, CursoEntity, CursoFiltro, Long> service;

    @Override
    public ResponseEntity<CursoDTO> criar(CursoDTO dto) {
        return this.service.criar(dto);
    }

    @Override
    public ResponseEntity<Page<CursoDTO>> buscarTodos(CursoFiltro filtro, Pageable paginacao) {
        return this.service.buscarTodos(filtro, paginacao);
    }

    @Override
    public ResponseEntity<CursoDTO> consultarPorId(Long id) {
        return this.service.consultarPorId(id);
    }

    @Override
    public ResponseEntity<CursoDTO> atualizar(Long id, CursoDTO dto) {
        return this.service.atualizar(id, dto);
    }

    @Override
    public ResponseEntity<?> deletarPorId(Long id) {
        return this.service.deletarPorId(id);
    }
}
