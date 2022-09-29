package com.devvadercursos.frameworks_drivers;

import com.devvadercursos.enterprise_business.entities.GenericsEntity;

public interface GenericsDatabase<T extends GenericsEntity<ID>, ID> {

    T salvar(T entidade);
    T atualizar(T entidade);
    void deletar(ID id);
}
