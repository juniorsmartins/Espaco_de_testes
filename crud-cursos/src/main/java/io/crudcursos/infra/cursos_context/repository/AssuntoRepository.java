package io.crudcursos.infra.cursos_context.repository;

import io.crudcursos.domain.cursos_context.entity.AssuntoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssuntoRepository extends JpaRepository<AssuntoEntity, Long> {}
