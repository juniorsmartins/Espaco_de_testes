package com.devvadercursos.application_business.usecases.services;

import com.devvadercursos.application_business.usecases.dtos.GenericsDTO;
import com.devvadercursos.enterprise_business.entities.GenericsEntity;
import org.springframework.http.ResponseEntity;

import java.awt.print.Pageable;

public interface ServiceGenerics<T extends GenericsDTO<ID>,E extends GenericsEntity<ID>, ID> {

    abstract ResponseEntity<T> cadastrar(T dto);
    abstract ResponseEntity<?> buscarTodos(Pageable paginacao, T filtro);
    abstract ResponseEntity<T> atualizar(ID id, T dto);
    abstract void deletar(ID id);
}
