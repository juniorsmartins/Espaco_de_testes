package io.crudcursos.domain.service;

import io.crudcursos.domain.dto.AssuntoDTO;
import io.crudcursos.domain.dto.CursoDTO;
import io.crudcursos.domain.entity.CursoEntity;
import io.crudcursos.domain.entity.filtros.CursoFiltro;
import io.crudcursos.domain.repository.AssuntoRepository;
import io.crudcursos.domain.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.Optional;

@Service
public non-sealed class CursoService extends AService<CursoDTO, CursoEntity, CursoFiltro, Long> {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private AssuntoRepository assuntoRepository;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public ResponseEntity<CursoDTO> criar(CursoDTO dto) {
        return Optional.of(dto)
                .map(cursoDTO -> {
                    var assuntoEntity = this.assuntoRepository.findById(cursoDTO.getAssunto().getId()).orElseThrow();
                    var cursoSalvo = this.cursoRepository.saveAndFlush(CursoEntity.builder()
                            .titulo(cursoDTO.getTitulo())
                            .instituicao(cursoDTO.getInstituicao())
                            .cargaHoraria(cursoDTO.getCargaHoraria())
                            .dataConclusao(cursoDTO.getDataConclusao())
                            .preco(cursoDTO.getPreco())
                            .link(cursoDTO.getLink())
                            .assunto(assuntoEntity)
                            .build());

                    return ResponseEntity
                            .created(URI.create("/" + cursoSalvo.getId()))
                            .body(CursoDTO.builder()
                                    .id(cursoSalvo.getId())
                                    .titulo(cursoSalvo.getTitulo())
                                    .instituicao(cursoSalvo.getInstituicao())
                                    .cargaHoraria(cursoSalvo.getCargaHoraria())
                                    .dataConclusao(cursoSalvo.getDataConclusao())
                                    .preco(cursoSalvo.getPreco())
                                    .link(cursoSalvo.getLink())
                                    .assunto(AssuntoDTO.builder()
                                            .id(cursoSalvo.getAssunto().getId())
                                            .assunto(cursoSalvo.getAssunto().getAssunto())
                                            .build())
                                    .build());
                })
                .orElseThrow();
    }

    @Override
    public ResponseEntity<Page<CursoDTO>> buscarTodos(CursoFiltro filtro, Pageable paginacao) {
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
