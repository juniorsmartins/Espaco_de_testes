package com.devvaderclientes.infra.repositories;

import com.devvaderclientes.domain.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepository extends JpaRepository<ClienteEntity, Long> {
}
