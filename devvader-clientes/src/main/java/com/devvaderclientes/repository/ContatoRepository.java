package com.devvaderclientes.repository;

import com.devvaderclientes.model.ContatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<ContatoEntity, Long> {
}
