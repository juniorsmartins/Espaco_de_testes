package com.devvadercursos.frameworks_drivers;

import com.devvadercursos.enterprise_business.entities.GenericsEntity;

public interface Database<E extends GenericsEntity<ID>, ID> {

    E salvar(E entidade);
    E atualizar(E entidade);
    void deletar(ID id);
}
