package com.devvadercursos.usecases;

import com.devvadercursos.usecases.dtos.request.DTOIn;
import com.devvadercursos.usecases.dtos.response.DTOOut;
import org.springframework.http.ResponseEntity;

import java.awt.print.Pageable;

public interface CursosService<IN extends DTOIn<ID>, OUT extends DTOOut<ID>, ID> {

    ResponseEntity<OUT> cadastrar(IN dtoIn);
    ResponseEntity<?> buscarTodos(Pageable paginacao, IN filtro);
    ResponseEntity<OUT> atualizar(ID id, IN dtoIn);
    void deletar(ID id);
}
