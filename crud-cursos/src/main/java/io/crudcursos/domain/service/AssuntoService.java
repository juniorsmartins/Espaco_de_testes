package io.crudcursos.domain.service;

import io.crudcursos.domain.dto.AssuntoDTO;
import io.crudcursos.domain.entity.AssuntoEntity;
import io.crudcursos.domain.entity.filtros.AssuntoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public final class AssuntoService implements IService<AssuntoDTO, AssuntoEntity, AssuntoFiltro, Long> {

    @Override
    public ResponseEntity<AssuntoDTO> cadastrar(AssuntoDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<Page<AssuntoDTO>> buscarTodos(Pageable paginacao, AssuntoFiltro filtro) {
        return null;
    }

    @Override
    public ResponseEntity<AssuntoDTO> consultarPorId(Long aLong) {
        return null;
    }

    @Override
    public ResponseEntity<AssuntoDTO> atualizarTotalOuSalvar(Long aLong, AssuntoDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<?> deletarPorId(Long aLong) {
        return null;
    }
}
