package com.devvadercursos.frameworks_drivers;

import com.devvadercursos.enterprise_business.entities.Curso;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursosRepository extends JpaRepository<Curso, Long> {

    Page<Curso> findAll(Example filtroConfigurado, Pageable paginacao);
}
