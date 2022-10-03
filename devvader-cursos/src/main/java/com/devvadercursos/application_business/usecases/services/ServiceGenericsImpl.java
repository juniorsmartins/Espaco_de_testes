package com.devvadercursos.application_business.usecases.services;

import com.devvadercursos.application_business.usecases.dtos.CursoDTO;
import com.devvadercursos.application_business.usecases.excecoes.MensagemPadrao;
import com.devvadercursos.application_business.usecases.excecoes.RecursoNaoEncontradoException;
import com.devvadercursos.enterprise_business.entities.Curso;
import com.devvadercursos.frameworks_drivers.CursosRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.net.URI;
import java.util.Optional;

@Service
public class ServiceGenericsImpl implements ServiceGenerics<CursoDTO, Curso, Long> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CursosRepository cursosRepository;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public ResponseEntity<CursoDTO> cadastrar(CursoDTO dtoIn) {
        return Optional.of(dtoIn)
                .map(cursoDTO -> modelMapper.map(cursoDTO, Curso.class))
                .map(curso -> cursosRepository.saveAndFlush(curso))
                .map(curso -> modelMapper.map(curso, CursoDTO.class))
                .map(cursoDTO -> ResponseEntity
                        .created(URI.create("/" + cursoDTO.getId()))
                        .body(cursoDTO))
                .orElseThrow();
    }

    @Override
    public ResponseEntity<?> buscarTodos(Pageable paginacao, CursoDTO filtro) {
        return null;
    }

    @Override
    public ResponseEntity<CursoDTO> consultarPorId(Long id) {
        return cursosRepository.findById(id)
                .map(curso -> modelMapper.map(curso, CursoDTO.class))
                .map(cursoDTO -> ResponseEntity
                        .ok()
                        .body(cursoDTO))
                .orElseThrow(() -> new RecursoNaoEncontradoException(MensagemPadrao.ID_NAO_ENCONTRADO));
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public ResponseEntity<CursoDTO> atualizarTotalOuSalvar(Long id, CursoDTO dtoIn) {
        return Optional.of(dtoIn)
                .map(cursoDTO -> modelMapper.map(cursoDTO, Curso.class))
                .map(curso -> {
                    curso.setId(id);
                    return cursosRepository.saveAndFlush(curso);
                })
                .map(curso -> ResponseEntity
                        .ok()
                        .body(modelMapper.map(curso, CursoDTO.class)))
                .orElseThrow();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public ResponseEntity<CursoDTO> atualizarParcialOuLancarExcecao(Long id, CursoDTO dtoIn) {
        return cursosRepository.findById(id)
                .map(curso -> {
                    var cursoEntity = modelMapper.map(dtoIn, Curso.class);
                    cursoEntity.setId(curso.getId());
                    cursosRepository.saveAndFlush(cursoEntity);
                    var cursoSaida = modelMapper.map(cursoEntity, CursoDTO.class);
                    return ResponseEntity.ok().body(cursoSaida);
                }).orElseThrow(() -> new RecursoNaoEncontradoException(MensagemPadrao.ID_NAO_ENCONTRADO));
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public ResponseEntity<?> deletarPorId(Long id) {
        return cursosRepository.findById(id)
                        .map(curso -> {
                            cursosRepository.delete(curso);
                            return ResponseEntity
                                    .ok()
                                    .body(MensagemPadrao.RECURSO_DELETADO);
                        }).orElseThrow(() -> new RecursoNaoEncontradoException(MensagemPadrao.ID_NAO_ENCONTRADO));
    }
}
