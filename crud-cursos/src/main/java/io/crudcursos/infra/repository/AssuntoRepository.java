package io.crudcursos.infra.repository;

import io.crudcursos.domain.entity.AssuntoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssuntoRepository extends JpaRepository<AssuntoEntity, Long> {}
