package com.devvadercursos.application_business.usecases.services;

import com.devvadercursos.application_business.usecases.dtos.CursoDTO;
import com.devvadercursos.enterprise_business.entities.Curso;
import com.devvadercursos.frameworks_drivers.GenericsDatabase;
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
public class ServiceGenericsImpl extends ServiceGenerics<CursoDTO, Curso, Long> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private GenericsDatabase genericsDatabase;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public ResponseEntity<CursoDTO> cadastrar(CursoDTO dtoIn) {
        return Optional.of(dtoIn)
                .map(cursoDTO -> modelMapper.map(cursoDTO, Curso.class))
                .map(curso -> genericsDatabase.salvar(curso))
                .map(curso -> modelMapper.map(curso, CursoDTO.class))
                .map(cursoDTO -> ResponseEntity.created(URI.create("/" + cursoDTO.getId())).body(cursoDTO))
                .orElseThrow();
    }

    @Override
    public ResponseEntity<?> buscarTodos(Pageable paginacao, CursoDTO filtro) {
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public ResponseEntity<CursoDTO> atualizar(Long id, CursoDTO dtoIn) {
        return null;
    }

    @Override
    public void deletar(Long id) {

    }
}
