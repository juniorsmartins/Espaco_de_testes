package com.devvaderclientes.resources.repository;

import com.devvaderclientes.domain.model.ContatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<ContatoEntity, Long> {
}
