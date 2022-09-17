package com.devvadercursos.usecases;

import com.devvadercursos.entities.Curso;
import com.devvadercursos.usecases.dtos.request.CursoDTOIn;
import com.devvadercursos.usecases.dtos.request.DTOIn;
import com.devvadercursos.usecases.dtos.response.CursoDTOOut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
public class CursosServiceImpl implements CursosService<CursoDTOIn, CursoDTOOut, Long> {

    @Override
    public ResponseEntity<CursoDTOOut> cadastrar(CursoDTOIn dtoIn) {
        return null;
    }

    @Override
    public ResponseEntity<?> buscarTodos(Pageable paginacao, CursoDTOIn filtro) {
        return null;
    }

    @Override
    public ResponseEntity<CursoDTOOut> atualizar(Long id, CursoDTOIn dtoIn) {
        return null;
    }

    @Override
    public void deletar(Long id) {

    }
}
