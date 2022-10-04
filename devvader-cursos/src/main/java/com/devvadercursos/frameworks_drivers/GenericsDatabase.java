package com.devvadercursos.frameworks_drivers;

import com.devvadercursos.enterprise_business.entities.IGenericsEntity;

import java.util.Optional;

public interface GenericsDatabase<T extends IGenericsEntity<ID>, ID> {

    T salvar(T entidade);
    T atualizar(T entidade);
    Optional<T> consultarPorId(ID id);
    void deletar(ID id);
}
