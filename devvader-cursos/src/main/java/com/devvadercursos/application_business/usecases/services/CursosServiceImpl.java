package com.devvadercursos.application_business.usecases.services;

import com.devvadercursos.application_business.usecases.dtos.request.CursoDTOIn;
import com.devvadercursos.application_business.usecases.dtos.response.CursoDTOOut;
import com.devvadercursos.enterprise_business.entities.Curso;
import com.devvadercursos.frameworks_drivers.Database;
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
public final class CursosServiceImpl extends CursosService<CursoDTOIn, Curso, CursoDTOOut, Long> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Database cursosDatabase;

//    @Override
//    public CursosDatabase<Curso, Long> getCursosDatabase() {
//        return cursosDatabase;
//    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public ResponseEntity<CursoDTOOut> cadastrar(CursoDTOIn dtoIn) {
        return Optional.of(dtoIn)
                .map(cursoDTOIn -> modelMapper.map(cursoDTOIn, Curso.class))
                .map(curso -> cursosDatabase.salvar(curso))
                .map(curso -> modelMapper.map(curso, CursoDTOOut.class))
                .map(cursoDTOOut -> ResponseEntity.created(URI.create("/" + cursoDTOOut.getId())).body(cursoDTOOut))
                .orElseThrow();
    }

    @Override
    public ResponseEntity<?> buscarTodos(Pageable paginacao, CursoDTOIn filtro) {
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public ResponseEntity<CursoDTOOut> atualizar(Long id, CursoDTOIn dtoIn) {
        return null;
    }

    @Override
    public void deletar(Long id) {

    }
}
