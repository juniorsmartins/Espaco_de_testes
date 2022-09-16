package com.devvadercursos.usecases;

import com.devvadercursos.entities.Curso;
import com.devvadercursos.usecases.dtos.request.CursoDTOIn;
import com.devvadercursos.usecases.dtos.request.DTOIn;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
public class CursosServiceImpl implements CursosService<Curso, CursoDTOIn, Long> {

    @Override
    public ResponseEntity<?> cadastrar(CursoDTOIn dtoIn) {
        return null;
    }

    @Override
    public ResponseEntity<?> buscarTodos(Pageable paginacao, CursoDTOIn filtro) {
        return null;
    }

    @Override
    public ResponseEntity<?> atualizar(Long id, CursoDTOIn dtoIn) {
        return null;
    }

    @Override
    public void deletar(Long id) {

    }
}
