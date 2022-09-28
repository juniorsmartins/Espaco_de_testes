package com.devvadercursos.application_business.usecases.services;

import com.devvadercursos.application_business.usecases.dtos.request.DTOIn;
import com.devvadercursos.application_business.usecases.dtos.response.DTOOut;
import com.devvadercursos.enterprise_business.entities.Entidade;
import org.springframework.http.ResponseEntity;

import java.awt.print.Pageable;

public abstract class CursosService<IN extends DTOIn<ID>,E extends Entidade<ID>, OUT extends DTOOut<ID>, ID> {

//    CursosDatabase<E, ID> getCursosDatabase();

    abstract ResponseEntity<OUT> cadastrar(IN dtoIn);
    abstract ResponseEntity<?> buscarTodos(Pageable paginacao, IN filtro);
    abstract ResponseEntity<OUT> atualizar(ID id, IN dtoIn);
    abstract void deletar(ID id);
}
