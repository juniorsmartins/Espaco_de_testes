package com.devvadercursos.frameworks_drivers;

import com.devvadercursos.enterprise_business.entities.Entidade;

public interface Database<E extends Entidade<ID>, ID> {

    E salvar(E entidade);
    E atualizar(E entidade);
    void deletar(ID id);
}
