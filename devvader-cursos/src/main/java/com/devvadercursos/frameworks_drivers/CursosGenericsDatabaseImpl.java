package com.devvadercursos.frameworks_drivers;

import com.devvadercursos.enterprise_business.entities.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public final class CursosDatabaseImpl implements Database<Curso, Long> {

    @Autowired
    private CursosRepository cursosRepository;

    @Override
    public Curso salvar(Curso curso) {
        return cursosRepository.saveAndFlush(curso);
    }

    @Override
    public Curso atualizar(Curso curso) {
        return cursosRepository.saveAndFlush(curso);
    }

    @Override
    public void deletar(Long id) {
        cursosRepository.deleteById(id);
    }
}
