package com.devvadercursos.application_business.usecases.services;

import com.devvadercursos.application_business.usecases.dtos.GenericsDTO;
import com.devvadercursos.enterprise_business.entities.GenericsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ServiceGenerics<T extends GenericsDTO<ID>,F extends GenericsDTO<ID>, E extends GenericsEntity<ID>, ID> {

    ResponseEntity<T> cadastrar(T dto);
    ResponseEntity<Page<T>> buscarTodos(Pageable paginacao, F filtro);
    ResponseEntity<T> consultarPorId(ID id);
    ResponseEntity<T> atualizarTotalOuSalvar(ID id, T dto);
    ResponseEntity<T> atualizarParcialOuLancarExcecao(ID id, T dto);
    ResponseEntity<?> deletarPorId(ID id);
}
