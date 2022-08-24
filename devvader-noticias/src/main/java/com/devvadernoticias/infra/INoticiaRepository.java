package com.devvadernoticias.infra;

import com.devvadernoticias.domain.entities.NoticiaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INoticiaRepository extends JpaRepository<NoticiaEntity, Long> {
}
