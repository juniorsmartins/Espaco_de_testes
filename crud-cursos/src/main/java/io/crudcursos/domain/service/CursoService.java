package io.crudcursos.domain.service;

import io.crudcursos.domain.dto.CursoDTO;
import io.crudcursos.domain.entity.CursoEntity;
import io.crudcursos.domain.entity.filtros.CursoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public final class CursoService implements IService<CursoDTO, CursoEntity, CursoFiltro, Long> {

    @Override
    public ResponseEntity<CursoDTO> cadastrar(CursoDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<Page<CursoDTO>> buscarTodos(Pageable paginacao, CursoFiltro filtro) {
        return null;
    }

    @Override
    public ResponseEntity<CursoDTO> consultarPorId(Long aLong) {
        return null;
    }

    @Override
    public ResponseEntity<CursoDTO> atualizarTotalOuSalvar(Long aLong, CursoDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<?> deletarPorId(Long aLong) {
        return null;
    }
}
