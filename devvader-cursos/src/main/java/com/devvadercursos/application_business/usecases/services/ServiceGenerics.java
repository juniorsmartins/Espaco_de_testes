package com.devvadercursos.application_business.usecases.services;

import com.devvadercursos.application_business.usecases.dtos.GenericsDTO;
import com.devvadercursos.enterprise_business.entities.GenericsEntity;
import org.springframework.http.ResponseEntity;

import java.awt.print.Pageable;

public interface ServiceGenerics<T extends GenericsDTO<ID>,E extends GenericsEntity<ID>, ID> {

    ResponseEntity<T> cadastrar(T dto);
    ResponseEntity<?> buscarTodos(Pageable paginacao, T filtro);
    ResponseEntity<T> atualizar(ID id, T dto);
    void deletar(ID id);
}
