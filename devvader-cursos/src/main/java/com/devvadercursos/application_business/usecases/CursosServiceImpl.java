package com.devvadercursos.application_business.usecases;

import com.devvadercursos.enterprise_business.entities.Curso;
import com.devvadercursos.frameworks_drivers.CursosDatabase;
import com.devvadercursos.application_business.usecases.dtos.request.CursoDTOIn;
import com.devvadercursos.application_business.usecases.dtos.response.CursoDTOOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;

@Service
public class CursosServiceImpl implements CursosService<CursoDTOIn, Curso, CursoDTOOut, Long> {

    @Autowired
    private CursosDatabase cursosDatabase;

    @Override
    public CursosDatabase<Curso, Long> getCursosDatabase() {
        return cursosDatabase;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public ResponseEntity<CursoDTOOut> cadastrar(CursoDTOIn dtoIn) {
        return null;
    }

    @Override
    public ResponseEntity<?> buscarTodos(Pageable paginacao, CursoDTOIn filtro) {
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public ResponseEntity<CursoDTOOut> atualizar(Long id, CursoDTOIn dtoIn) {
        return null;
    }

    @Override
    public void deletar(Long id) {

    }
}
