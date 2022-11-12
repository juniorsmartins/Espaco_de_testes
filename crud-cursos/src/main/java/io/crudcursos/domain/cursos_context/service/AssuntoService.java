package io.crudcursos.domain.cursos_context.service;

import io.crudcursos.domain.cursos_context.dto.AssuntoDTOResponse;
import io.crudcursos.domain.cursos_context.dto.AssuntoDTORequest;
import io.crudcursos.domain.cursos_context.entity.AssuntoEntity;
import io.crudcursos.domain.cursos_context.entity.filtros.AssuntoFiltro;
import io.crudcursos.domain.excecoes.MensagensPadrao;
import io.crudcursos.domain.excecoes.RecursoNaoEncontradoException;
import io.crudcursos.infra.cursos_context.repository.AssuntoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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
public non-sealed class AssuntoService extends AService<AssuntoDTORequest, AssuntoDTOResponse, AssuntoFiltro, AssuntoEntity, Long> {

    @Autowired
    private AssuntoRepository assuntoRepository;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public ResponseEntity<AssuntoDTOResponse> criar(AssuntoDTORequest dto) {
        return Optional.of(dto)
                .map(AssuntoEntity::new)
                .map(assuntoEntity -> this.assuntoRepository.saveAndFlush(assuntoEntity))
                .map(AssuntoDTOResponse::new)
                .map(assuntoDTOResponse -> ResponseEntity
                        .created(URI.create("/" + assuntoDTOResponse.getId()))
                        .body(assuntoDTOResponse)
                )
                .orElseThrow(() -> new NullPointerException(MensagensPadrao.ASSUNTO_NULO));
    }

    @Override
    public ResponseEntity<Page<AssuntoDTOResponse>> buscarTodos(AssuntoFiltro filtro, Pageable paginacao) {
        return ResponseEntity
                .ok()
                .body(this.assuntoRepository.findAll(configurarFiltro(filtro), paginacao)
                        .map(AssuntoDTOResponse::new));
    }

        private Example<AssuntoEntity> configurarFiltro(AssuntoFiltro filtro) {
            // ExampleMatcher - permite configurar condições para serem aplicadas nos filtros
            ExampleMatcher exampleMatcher = ExampleMatcher
                    .matchingAll()
                    .withIgnoreCase() // Ignorar caixa alta ou baixa - quando String
                    .withIgnoreNullValues() // Ignorar valores nulos
                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); // permite encontrar palavras parecidas - tipo Like do SQL
            // Example - pega campos populados para criar filtros
            return Example.of(AssuntoEntity.builder()
                    .id(filtro.getId())
                    .tema(filtro.getTema())
                    .build(), exampleMatcher);
        }

    @Override
    public ResponseEntity<AssuntoDTOResponse> consultarPorId(Long id) {
        return this.assuntoRepository.findById(id)
                .map(AssuntoDTOResponse::new)
                .map(assuntoDTOResponse ->
                    ResponseEntity
                            .ok()
                            .body(assuntoDTOResponse)
                )
                .orElseThrow(() -> new RecursoNaoEncontradoException(MensagensPadrao.ASSUNTO_NAO_ENCONTRADO));
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public ResponseEntity<AssuntoDTOResponse> atualizar(Long id, AssuntoDTORequest dto) {
        return this.assuntoRepository.findById(id)
                .map(assuntoDoDatabase -> this.assuntoRepository.saveAndFlush(AssuntoEntity.builder()
                        .id(assuntoDoDatabase.getId())
                        .tema(dto.tema())
                        .build())
                )
                .map(AssuntoDTOResponse::new)
                .map(assuntoDTOResponse -> ResponseEntity
                        .ok()
                        .body(assuntoDTOResponse)
                )
                .orElseThrow(() -> new RecursoNaoEncontradoException(MensagensPadrao.ASSUNTO_NAO_ENCONTRADO));
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public ResponseEntity<?> deletarPorId(Long id) {
        return this.assuntoRepository.findById(id)
                .map(assuntoEntity -> {
                   this.assuntoRepository.deleteById(assuntoEntity.getId());
                   return ResponseEntity
                           .ok()
                           .body(MensagensPadrao.ASSUNTO_DELETADO);
                })
                .orElseThrow(() -> new RecursoNaoEncontradoException(MensagensPadrao.ASSUNTO_NAO_ENCONTRADO));
    }
}
