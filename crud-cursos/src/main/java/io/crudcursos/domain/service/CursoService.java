package io.crudcursos.domain.service;

import io.crudcursos.domain.dto.AssuntoDTOResponse;
import io.crudcursos.domain.dto.CursoDTORequest;
import io.crudcursos.domain.dto.CursoDTOResponse;
import io.crudcursos.domain.entity.CursoEntity;
import io.crudcursos.domain.entity.filtros.CursoFiltro;
import io.crudcursos.domain.excecoes.MensagensPadrao;
import io.crudcursos.domain.excecoes.RecursoNaoEncontradoException;
import io.crudcursos.infra.repository.AssuntoRepository;
import io.crudcursos.infra.repository.CursoRepository;
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
public non-sealed class CursoService extends AService<CursoDTORequest, CursoDTOResponse, CursoFiltro, CursoEntity, Long> {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private AssuntoRepository assuntoRepository;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public ResponseEntity<CursoDTOResponse> criar(CursoDTORequest dto) {
        return Optional.of(dto)
                .map(CursoEntity::new)
                .map(cursoEntityNovo -> {
                    var assuntoEncontrado = this.assuntoRepository.findById(cursoEntityNovo.getAssunto().getId())
                            .orElseThrow(() -> new RecursoNaoEncontradoException(MensagensPadrao.ASSUNTO_NAO_ENCONTRADO));
                    cursoEntityNovo.setAssunto(assuntoEncontrado);
                    return this.cursoRepository.saveAndFlush(cursoEntityNovo);
                })
                .map(CursoDTOResponse::new)
                .map(cursoDTOResponse -> ResponseEntity
                        .created(URI.create("/" + cursoDTOResponse.getId()))
                        .body(cursoDTOResponse)
                )
                .orElseThrow(() -> new NullPointerException(MensagensPadrao.CURSO_NULO));
    }

    @Override
    public ResponseEntity<Page<CursoDTOResponse>> buscarTodos(CursoFiltro filtro, Pageable paginacao) {
        return ResponseEntity
                .ok()
                .body(this.cursoRepository.findAll(configurarFiltro(filtro), paginacao)
                        .map(cursoEntity -> CursoDTOResponse.builder()
                                .id(cursoEntity.getId())
                                .titulo(cursoEntity.getTitulo())
                                .instituicao(cursoEntity.getInstituicao())
                                .cargaHoraria(cursoEntity.getCargaHoraria())
                                .dataConclusao(cursoEntity.getDataConclusao())
                                .preco(cursoEntity.getPreco())
                                .link(cursoEntity.getLink())
                                .assunto(AssuntoDTOResponse.builder()
                                        .id(cursoEntity.getAssunto().getId())
                                        .tema(cursoEntity.getAssunto().getTema())
                                        .build())
                                .build()));
    }

        private Example<CursoEntity> configurarFiltro(CursoFiltro filtro) {
            // ExampleMatcher - permite configurar condições para serem aplicadas nos filtros
            ExampleMatcher exampleMatcher = ExampleMatcher
                    .matchingAll()
                    .withIgnoreCase() // Ignorar caixa alta ou baixa - quando String
                    .withIgnoreNullValues() // Ignorar valores nulos
                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); // permite encontrar palavras parecidas - tipo Like do SQL

            // Example - pega campos populados para criar filtros
            return Example.of(CursoEntity.builder()
                    .id(filtro.getId())
                    .titulo(filtro.getTitulo())
                    .instituicao(filtro.getInstituicao())
                    .cargaHoraria(filtro.getCargaHoraria())
                    .dataConclusao(filtro.getDataConclusao())
                    .preco(filtro.getPreco())
                    .link(filtro.getLink())
                    .build(), exampleMatcher);
        }

    @Override
    public ResponseEntity<CursoDTOResponse> consultarPorId(Long id) {
        return this.cursoRepository.findById(id)
                .map(CursoDTOResponse::new)
                .map(cursoDTOResponse ->
                    ResponseEntity
                            .ok()
                            .body(cursoDTOResponse)
                )
                .orElseThrow(() -> new RecursoNaoEncontradoException(MensagensPadrao.CURSO_NAO_ENCONTRADO));
    }

    @Override
    public ResponseEntity<CursoDTOResponse> atualizar(Long id, CursoDTORequest dto) {
        return this.cursoRepository.findById(id)
                .map(cursoDoDatabase -> {
                    var assuntoDoDatabase = this.assuntoRepository.findById(dto.assunto().id())
                            .orElseThrow(() -> new RecursoNaoEncontradoException(MensagensPadrao.ASSUNTO_NAO_ENCONTRADO));
                    // cursoDoDatabase.setAssunto(assuntoDoDatabase);
                    return this.cursoRepository.saveAndFlush(CursoEntity.builder()
                            .id(cursoDoDatabase.getId())
                            .titulo(dto.titulo())
                            .instituicao(dto.instituicao())
                            .cargaHoraria(dto.cargaHoraria())
                            .dataConclusao(dto.dataConclusao())
                            .preco(dto.preco())
                            .link(dto.link())
                            .assunto(assuntoDoDatabase)
                            .build());
                })
                .map(CursoDTOResponse::new)
                .map(cursoDTOResponse ->
                    ResponseEntity
                        .ok()
                        .body(cursoDTOResponse)
                )
                .orElseThrow(() -> new RecursoNaoEncontradoException(MensagensPadrao.CURSO_NAO_ENCONTRADO));
    }

    @Override
    public ResponseEntity<?> deletarPorId(Long id) {
        return this.cursoRepository.findById(id)
                .map(cursoEntity -> {
                    this.cursoRepository.deleteById(cursoEntity.getId());
                    return ResponseEntity
                            .ok()
                            .body(MensagensPadrao.CURSO_DELETADO);
                })
                .orElseThrow();
    }
}
