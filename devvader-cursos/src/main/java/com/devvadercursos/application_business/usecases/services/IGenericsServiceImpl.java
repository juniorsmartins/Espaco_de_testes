package com.devvadercursos.application_business.usecases.services;

import com.devvadercursos.application_business.usecases.dtos.CursoDTOI;
import com.devvadercursos.application_business.usecases.dtos.FiltroBuscarTodos;
import com.devvadercursos.application_business.usecases.excecoes.MensagemPadrao;
import com.devvadercursos.application_business.usecases.excecoes.RecursoNaoEncontradoException;
import com.devvadercursos.application_business.usecases.excecoes.RegraDeNegocioException;
import com.devvadercursos.enterprise_business.entities.Curso;
import com.devvadercursos.frameworks_drivers.ICursosRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
@Slf4j
public class IGenericsServiceImpl implements IGenericsService<CursoDTOI, FiltroBuscarTodos, Curso, Long> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ICursosRepository ICursosRepository;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public ResponseEntity<CursoDTOI> cadastrar(CursoDTOI dtoIn) {
        return Optional.of(dtoIn)
                .map(cursoDTO -> modelMapper.map(cursoDTO, Curso.class))
                .map(curso -> ICursosRepository.saveAndFlush(curso))
                .map(curso -> modelMapper.map(curso, CursoDTOI.class))
                .map(cursoDTO -> ResponseEntity
                        .created(URI.create("/" + cursoDTO.getId()))
                        .body(cursoDTO))
                .orElseThrow(() -> new RegraDeNegocioException(MensagemPadrao.REGRA_DE_NEGOCIO_QUEBRADA));
    }

    @Override
    public ResponseEntity<Page<CursoDTOI>> buscarTodos(Pageable paginacao, FiltroBuscarTodos filtro) {
        if(filtro == null)
            return ResponseEntity
                    .ok()
                    .body(ICursosRepository.findAll(paginacao).map(curso -> modelMapper.map(curso, CursoDTOI.class)));

        return ResponseEntity
                .ok()
                .body(ICursosRepository.findAll(configurarFiltro(filtro), paginacao)
                        .map(curso -> modelMapper.map(curso, CursoDTOI.class)));
    }

        private Example<Curso> configurarFiltro(FiltroBuscarTodos filtro) {
            // ExampleMatcher - permite configurar condições para serem aplicadas nos filtros
            ExampleMatcher exampleMatcher = ExampleMatcher
                    .matchingAll()
                    .withIgnoreCase() // Ignorar caixa alta ou baixa - quando String
                    .withIgnoreNullValues() // Ignorar valores nulos
                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) // permite encontrar palavras parecidas - tipo Like do SQL
                    .withIgnorePaths("dataInicio"); // Ignorar atributo ou atributos específicos

            // Example - pega campos populados para criar filtros
            return Example.of(modelMapper.map(filtro, Curso.class), exampleMatcher);
        }

    @Override
    public ResponseEntity<CursoDTOI> consultarPorId(Long id) {
        return ICursosRepository.findById(id)
                .map(curso -> modelMapper.map(curso, CursoDTOI.class))
                .map(cursoDTO -> ResponseEntity
                        .ok()
                        .body(cursoDTO))
                .orElseThrow(() -> new RecursoNaoEncontradoException(MensagemPadrao.ID_NAO_ENCONTRADO));
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public ResponseEntity<CursoDTOI> atualizarTotalOuSalvar(Long id, CursoDTOI dtoIn) {
        return Optional.of(dtoIn)
                .map(cursoDTO -> modelMapper.map(cursoDTO, Curso.class))
                .map(curso -> {
                    curso.setId(id);
                    ICursosRepository.saveAndFlush(curso);
                    return ResponseEntity
                            .ok()
                            .body(modelMapper.map(curso, CursoDTOI.class));
                }).orElseThrow();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public ResponseEntity<CursoDTOI> atualizarParcialOuLancarExcecao(Long id, CursoDTOI dtoIn) {
        return ICursosRepository.findById(id)
                .map(curso -> {
                    curso.setTitulo(dtoIn.getTitulo());
                    curso.setDescricao(dtoIn.getDescricao());
                    curso.setDataInicio(dtoIn.getDataInicio());
                    curso.setDataFim(dtoIn.getDataFim());
                    return ResponseEntity
                            .ok()
                            .body(modelMapper.map(curso, CursoDTOI.class));
                }).orElseThrow(() -> new RecursoNaoEncontradoException(MensagemPadrao.ID_NAO_ENCONTRADO));
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public ResponseEntity<?> deletarPorId(Long id) {
        return ICursosRepository.findById(id)
                        .map(curso -> {
                            ICursosRepository.delete(curso);
                            return ResponseEntity
                                    .ok()
                                    .body(MensagemPadrao.RECURSO_DELETADO);
                        }).orElseThrow(() -> new RecursoNaoEncontradoException(MensagemPadrao.ID_NAO_ENCONTRADO));
    }
}
