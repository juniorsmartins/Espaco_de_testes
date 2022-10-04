package com.devvadercursos.frameworks_drivers;

import com.devvadercursos.enterprise_business.entities.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CursosDatabaseImpl implements GenericsDatabase<Curso, Long> {

    @Autowired
    private ICursosRepository ICursosRepository;

    @Override
    public Curso salvar(Curso curso) {
        return ICursosRepository.saveAndFlush(curso);
    }

    @Override
    public Curso atualizar(Curso curso) {
        return ICursosRepository.saveAndFlush(curso);
    }

    @Override
    public Optional<Curso> consultarPorId(Long id) {
        return ICursosRepository.findById(id);
    }

    @Override
    public void deletar(Long id) {
        ICursosRepository.deleteById(id);
    }
}
