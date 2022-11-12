package io.crudcursos.cursos_contexto.infra.repository;

import io.crudcursos.cursos_contexto.dominio.entity.AssuntoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssuntoRepository extends JpaRepository<AssuntoEntity, Long> {}
