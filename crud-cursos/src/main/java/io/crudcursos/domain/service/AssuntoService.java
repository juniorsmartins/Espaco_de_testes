package io.crudcursos.domain.service;

import io.crudcursos.domain.dto.AssuntoDTO;
import io.crudcursos.domain.entity.AssuntoEntity;
import io.crudcursos.domain.entity.filtros.AssuntoFiltro;
import io.crudcursos.domain.repository.AssuntoRepository;
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
public non-sealed class AssuntoService extends AService<AssuntoDTO, AssuntoEntity, AssuntoFiltro, Long> {

    @Autowired
    private AssuntoRepository assuntoRepository;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public ResponseEntity<AssuntoDTO> criar(AssuntoDTO dto) {
        return Optional.of(dto)
                .map(assuntoDTO -> {
                    var assuntoSalvo = this.assuntoRepository.saveAndFlush(AssuntoEntity.builder()
                            .assunto(assuntoDTO.getAssunto())
                            .build());
                    return ResponseEntity
                            .created(URI.create("/" + assuntoSalvo.getId()))
                            .body(AssuntoDTO.builder()
                                    .id(assuntoSalvo.getId())
                                    .assunto(assuntoSalvo.getAssunto())
                                    .build());
                })
                .orElseThrow();
    }

    @Override
    public ResponseEntity<Page<AssuntoDTO>> buscarTodos(Pageable paginacao, AssuntoFiltro filtro) {
        return null;
    }

    @Override
    public ResponseEntity<AssuntoDTO> consultarPorId(Long id) {
        return this.assuntoRepository.findById(id)
                .map(assuntoSalvo -> ResponseEntity
                        .ok()
                        .body(AssuntoDTO.builder()
                                .id(assuntoSalvo.getId())
                                .assunto(assuntoSalvo.getAssunto())
                                .build())
                )
                .orElseThrow();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public ResponseEntity<AssuntoDTO> atualizar(Long id, AssuntoDTO dto) {
        return this.assuntoRepository.findById(id)
                .map(assunto -> {
                    assunto.setAssunto(dto.getAssunto());
                    return ResponseEntity
                            .ok()
                            .body(AssuntoDTO.builder()
                                    .id(assunto.getId())
                                    .assunto(assunto.getAssunto())
                                    .build());
                })
                .orElseThrow();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public ResponseEntity<?> deletarPorId(Long id) {
        return this.assuntoRepository.findById(id)
                .map(assunto -> {
                   this.assuntoRepository.deleteById(id);
                   return ResponseEntity
                           .ok()
                           .body("Recurso Deletado!");
                })
                .orElseThrow();
    }
}
