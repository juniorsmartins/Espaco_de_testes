package com.devvaderclientes.resources.repositories;

import com.devvaderclientes.domain.entities.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEnderecoRepository extends JpaRepository<EnderecoEntity, Long> {
}
