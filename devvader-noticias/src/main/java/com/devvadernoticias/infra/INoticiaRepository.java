package com.devvadernoticias.infra;

import com.devvadernoticias.domain.entities.NoticiaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface INoticiaRepository extends JpaRepository<NoticiaEntity, Long> {

    List<NoticiaEntity> findAllByCliente(Long cliente);
}
