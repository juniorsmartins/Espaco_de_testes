package com.devvadercursos.frameworks_drivers;

import com.devvadercursos.enterprise_business.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursosRepository extends JpaRepository<Curso, Long> {
}
