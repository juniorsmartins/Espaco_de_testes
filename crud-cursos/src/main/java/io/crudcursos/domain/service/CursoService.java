package io.crudcursos.domain.service;

import io.crudcursos.domain.dto.AssuntoDTO;
import io.crudcursos.domain.dto.AssuntoDTOResponse;
import io.crudcursos.domain.dto.CursoDTO;
import io.crudcursos.domain.dto.CursoDTOResponse;
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
public non-sealed class CursoService extends AService<CursoDTO, CursoDTOResponse, CursoEntity, CursoFiltro, Long> {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private AssuntoRepository assuntoRepository;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public ResponseEntity<CursoDTOResponse> criar(CursoDTO dto) {
        return Optional.of(dto)
                .map(cursoNovo -> {
                    this.assuntoRepository.findById(cursoNovo.getAssunto().getId())
                            .orElseThrow(() -> new RecursoNaoEncontradoException(MensagensPadrao.ASSUNTO_NAO_ENCONTRADO));

                    var cursoSalvo = this.cursoRepository.saveAndFlush(new CursoEntity(cursoNovo));

                    return ResponseEntity
                            .created(URI.create("/" + cursoSalvo.getId()))
                            .body(new CursoDTOResponse(cursoSalvo.getId(), cursoSalvo.getTitulo(),
                                            cursoSalvo.getInstituicao(), cursoSalvo.getCargaHoraria(),
                                            cursoSalvo.getDataConclusao(), cursoSalvo.getPreco(),
                                            cursoSalvo.getLink(), new AssuntoDTOResponse(
                                                    cursoSalvo.getAssunto().getId(), cursoSalvo.getAssunto().getTema())));
                })
                .orElseThrow();
    }

    @Override
    public ResponseEntity<Page<CursoDTO>> buscarTodos(CursoFiltro filtro, Pageable paginacao) {
        System.out.println("----- buscarTodos Service -----");
        return ResponseEntity
                .ok()
                .body(this.cursoRepository.findAll(configurarFiltro(filtro), paginacao)
                    .map(cursoEntity ->
                        {
                            System.out.println("----- buscarTodos Map -----");
                            var cursoDTO = CursoDTO.builder()
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
    public ResponseEntity<CursoDTO> consultarPorId(Long id) {
        return this.cursoRepository.findById(id)
                .map(cursoEntity ->
                    ResponseEntity
                            .ok()
                            .body(CursoDTO.builder()
                                    .id(cursoEntity.getId())
                                    .titulo(cursoEntity.getTitulo())
                                    .instituicao(cursoEntity.getInstituicao())
                                    .cargaHoraria(cursoEntity.getCargaHoraria())
                                    .dataConclusao(cursoEntity.getDataConclusao())
                                    .preco(cursoEntity.getPreco())
                                    .link(cursoEntity.getLink())
                                    .assunto(AssuntoDTO.builder()
                                            .id(cursoEntity.getAssunto().getId())
                                            .tema(cursoEntity.getAssunto().getTema())
                                            .build())
                                    .build())
                )
                .orElseThrow();
    }

    @Override
    public ResponseEntity<CursoDTO> atualizar(Long id, CursoDTO dto) {
        return this.cursoRepository.findById(id)
                .map(cursoEntity -> {
                    var assuntoAtualizado = this.assuntoRepository.findById(dto.getAssunto().getId()).orElseThrow();
                    var cursoAtualizado = this.cursoRepository.saveAndFlush(CursoEntity.builder()
                            .id(cursoEntity.getId())
                            .titulo(dto.getTitulo())
                            .instituicao(dto.getInstituicao())
                            .cargaHoraria(dto.getCargaHoraria())
                            .dataConclusao(dto.getDataConclusao())
                            .preco(dto.getPreco())
                            .link(dto.getLink())
                            .assunto(AssuntoEntity.builder()
                                    .id(assuntoAtualizado.getId())
                                    .build())
                            .build());

                    return ResponseEntity
                            .ok()
                            .body(CursoDTO.builder()
                                    .id(cursoAtualizado.getId())
                                    .titulo(cursoAtualizado.getInstituicao())
                                    .cargaHoraria(cursoAtualizado.getCargaHoraria())
                                    .dataConclusao(cursoAtualizado.getDataConclusao())
                                    .preco(cursoAtualizado.getPreco())
                                    .link(cursoAtualizado.getLink())
                                    .assunto(AssuntoDTO.builder()
                                            .id(assuntoAtualizado.getId())
                                            .tema(assuntoAtualizado.getTema())
                                            .build())
                                    .build());
                })
                .orElseThrow();
    }

    @Override
    public ResponseEntity<?> deletarPorId(Long id) {
        return this.cursoRepository.findById(id)
                .map(cursoEntity -> {
                    this.cursoRepository.deleteById(cursoEntity.getId());
                    return ResponseEntity
                            .ok()
                            .body("Curso Deletado!");
                })
                .orElseThrow();
    }
}
