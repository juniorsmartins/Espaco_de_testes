package io.crudcursos.cursos_contexto.infra.repository;

import io.crudcursos.cursos_contexto.dominio.entity.CursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<CursoEntity, Long> {}
