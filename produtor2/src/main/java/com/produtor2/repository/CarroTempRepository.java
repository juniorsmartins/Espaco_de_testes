package com.produtor2.repository;

import com.produtor2.entities.CarroTemporario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroTempRepository extends JpaRepository<CarroTemporario, Long> {
}
