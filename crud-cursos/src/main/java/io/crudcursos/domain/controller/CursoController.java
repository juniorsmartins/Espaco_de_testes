package io.crudcursos.domain.controller;

import io.crudcursos.domain.dto.CursoDTO;
import io.crudcursos.domain.entity.filtros.CursoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public final class CursoController implements IController<CursoDTO, CursoFiltro, Long> {

    @Override
    public ResponseEntity<CursoDTO> criar(CursoDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<Page<CursoDTO>> buscarTodos(Pageable pageable, CursoFiltro filtro) {
        return null;
    }

    @Override
    public ResponseEntity<CursoDTO> consultarPorId(Long aLong) {
        return null;
    }

    @Override
    public ResponseEntity<CursoDTO> atualizar(Long aLong, CursoDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<?> deletarPorId(Long aLong) {
        return null;
    }
}
