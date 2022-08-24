package com.devvaderclientes.infra.repositories;

import com.devvaderclientes.domain.entities.ContatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContatoRepository extends JpaRepository<ContatoEntity, Long> {
}
