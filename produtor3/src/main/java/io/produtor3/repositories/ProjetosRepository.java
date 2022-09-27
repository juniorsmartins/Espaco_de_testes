package io.produtor3.repositories;

import io.produtor3.entities.ProjetoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetosRepository extends JpaRepository<ProjetoEntity, Long> {
}
