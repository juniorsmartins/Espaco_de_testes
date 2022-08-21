package com.devvaderclientes.resources.c_repositories;

import com.devvaderclientes.domain.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
}
