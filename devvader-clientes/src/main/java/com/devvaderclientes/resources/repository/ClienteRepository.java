package com.devvaderclientes.resources.repository;

import com.devvaderclientes.domain.model.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
}
