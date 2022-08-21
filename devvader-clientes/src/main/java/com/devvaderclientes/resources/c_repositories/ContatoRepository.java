package com.devvaderclientes.resources.c_repositories;

import com.devvaderclientes.domain.entities.ContatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<ContatoEntity, Long> {
}
