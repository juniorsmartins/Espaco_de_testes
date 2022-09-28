package com.devvadercursos.application_business.usecases;

import com.devvadercursos.enterprise_business.entities.Entidade;
import com.devvadercursos.frameworks_drivers.CursosDatabase;
import com.devvadercursos.application_business.usecases.dtos.request.DTOIn;
import com.devvadercursos.application_business.usecases.dtos.response.DTOOut;
import org.springframework.http.ResponseEntity;

import java.awt.print.Pageable;

public interface CursosService<IN extends DTOIn<ID>,E extends Entidade<ID>, OUT extends DTOOut<ID>, ID> {

    CursosDatabase<E, ID> getCursosDatabase();

    ResponseEntity<OUT> cadastrar(IN dtoIn);
    ResponseEntity<?> buscarTodos(Pageable paginacao, IN filtro);
    ResponseEntity<OUT> atualizar(ID id, IN dtoIn);
    void deletar(ID id);
}
