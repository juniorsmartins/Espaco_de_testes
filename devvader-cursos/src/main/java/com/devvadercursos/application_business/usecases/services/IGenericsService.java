package com.devvadercursos.application_business.usecases.services;

import com.devvadercursos.application_business.usecases.dtos.IGenericsDTO;
import com.devvadercursos.enterprise_business.entities.IGenericsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IGenericsService<T extends IGenericsDTO<ID>, F extends IGenericsDTO<ID>, A extends IGenericsDTO<ID>, E extends IGenericsEntity<ID>, ID> {

    ResponseEntity<T> cadastrar(T dto);
    ResponseEntity<Page<T>> buscarTodos(Pageable paginacao, F filtro);
    ResponseEntity<T> consultarPorId(ID id);
    ResponseEntity<T> atualizarTotalOuSalvar(ID id, T dto);
    ResponseEntity<T> atualizarParcialOuLancarExcecao(ID id, A dto);
    ResponseEntity<?> deletarPorId(ID id);
}
