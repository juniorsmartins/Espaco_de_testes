package io.crudcursos.domain.controller;

import io.crudcursos.domain.dto.AssuntoDTO;
import io.crudcursos.domain.entity.filtros.AssuntoFiltro;
import io.crudcursos.domain.entity.filtros.IFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public final class AssuntoController implements IController<AssuntoDTO, AssuntoFiltro, Long> {

    @Override
    public ResponseEntity<AssuntoDTO> criar(AssuntoDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<Page<AssuntoDTO>> buscarTodos(Pageable pageable, AssuntoFiltro filtro) {
        return null;
    }


    @Override
    public ResponseEntity<AssuntoDTO> consultarPorId(Long aLong) {
        return null;
    }

    @Override
    public ResponseEntity<AssuntoDTO> atualizar(Long aLong, AssuntoDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<?> deletarPorId(Long aLong) {
        return null;
    }
}
