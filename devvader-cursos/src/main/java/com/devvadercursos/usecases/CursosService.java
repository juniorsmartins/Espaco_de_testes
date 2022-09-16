package com.devvadercursos.usecases;

import com.devvadercursos.entities.Entidade;
import com.devvadercursos.usecases.dtos.request.DTOIn;
import org.springframework.http.ResponseEntity;

import java.awt.print.Pageable;

public interface CursosService<T extends Entidade<ID>, DTO extends DTOIn<ID>, ID> {

    ResponseEntity<?> cadastrar(DTO dtoIn);
    ResponseEntity<?> buscarTodos(Pageable paginacao, DTO filtro);
    ResponseEntity<?> atualizar(Long id, DTO dtoIn);
    void deletar(Long id);
}
