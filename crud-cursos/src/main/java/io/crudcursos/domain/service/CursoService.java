package io.crudcursos.domain.service;

import io.crudcursos.domain.dto.AssuntoDTOResponse;
import io.crudcursos.domain.dto.AssuntoDTORequest;
import io.crudcursos.domain.dto.CursoDTOResponse;
import io.crudcursos.domain.dto.CursoDTORequest;
import io.crudcursos.domain.entity.AssuntoEntity;
import io.crudcursos.domain.entity.CursoEntity;
import io.crudcursos.domain.entity.filtros.CursoFiltro;
import io.crudcursos.domain.excecoes.MensagensPadrao;
import io.crudcursos.domain.excecoes.RecursoNaoEncontradoException;
import io.crudcursos.domain.repository.AssuntoRepository;
import io.crudcursos.domain.repository.CursoRepository;
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
                .map(cursoNovo -> {
                    this.assuntoRepository.findById(cursoNovo.assunto().id())
                            .orElseThrow(() -> new RecursoNaoEncontradoException(MensagensPadrao.ASSUNTO_NAO_ENCONTRADO));

                    var cursoSalvo = this.cursoRepository.saveAndFlush(new CursoEntity(cursoNovo));

                    return ResponseEntity
                            .created(URI.create("/" + cursoSalvo.getId()))
                            .body(new CursoDTOResponse(cursoSalvo));
                })
                .orElseThrow(() -> new NullPointerException(MensagensPadrao.CURSO_NULO));
    }

    @Override
    public ResponseEntity<Page<CursoDTOResponse>> buscarTodos(CursoFiltro filtro, Pageable paginacao) {
        System.out.println("----- buscarTodos Service -----");
        return ResponseEntity
                .ok()
                .body(this.cursoRepository.findAll(configurarFiltro(filtro), paginacao)
                    .map(cursoEntity ->
                        {
                            System.out.println("----- buscarTodos Map -----");
                            var cursoDTO = CursoDTOResponse.builder()
                                .id(cursoEntity.getId())
                                .titulo(cursoEntity.getTitulo())
                                .instituicao(cursoEntity.getInstituicao())
                                .cargaHoraria(cursoEntity.getCargaHoraria())
                                .dataConclusao(cursoEntity.getDataConclusao())
                                .preco(cursoEntity.getPreco())
                                .link(cursoEntity.getLink())
                                .build();
                            return cursoDTO;
                        }
                    )
                );
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
                .map(cursoDoDatabase ->
                    ResponseEntity
                            .ok()
                            .body(new CursoDTOResponse(cursoDoDatabase))
                )
                .orElseThrow(() -> new RecursoNaoEncontradoException(MensagensPadrao.CURSO_NAO_ENCONTRADO));
    }

    @Override
    public ResponseEntity<CursoDTOResponse> atualizar(Long id, CursoDTORequest dto) {
        return this.cursoRepository.findById(id)
                .map(cursoDoDatabase -> {
                    var assuntoDoDatabase = this.assuntoRepository.findById(dto.assunto().id())
                            .orElseThrow(() -> new RecursoNaoEncontradoException(MensagensPadrao.ASSUNTO_NAO_ENCONTRADO));

                    var cursoAtualizado = this.cursoRepository.saveAndFlush(CursoEntity.builder()
                            .id(cursoDoDatabase.getId())
                            .titulo(dto.titulo())
                            .instituicao(dto.instituicao())
                            .cargaHoraria(dto.cargaHoraria())
                            .dataConclusao(dto.dataConclusao())
                            .preco(dto.preco())
                            .link(dto.link())
                            .assunto(assuntoDoDatabase)
                            .build());

                    return ResponseEntity
                            .ok()
                            .body(new CursoDTOResponse(cursoAtualizado));
                })
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
