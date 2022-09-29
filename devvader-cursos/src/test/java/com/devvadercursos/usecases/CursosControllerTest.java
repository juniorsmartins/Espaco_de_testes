package com.devvadercursos.usecases;

import com.devvadercursos.interface_adapters.controllers.CursosControllerImpl;
import com.devvadercursos.application_business.usecases.dtos.CursoDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

@SpringBootTest
class CursosControllerTest {

    @Autowired
    private CursosControllerImpl cursosController;

    private CursoDTO cursoDTO;

    @BeforeEach
    void criadorDeCenariosDeTeste() {
        cursoDTO = CursoDTO.builder()
                .titulo("Tecnologia Java")
                .dataInicio(LocalDate.of(2020, 07, 15))
                .dataFim(LocalDate.of(2021, 07, 22))
                .build();
    }

    @Test
    void teste01_retornarPositivoQuando_cadastrarCursos() {
        var response = cursosController.cadastrar(cursoDTO);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(response, ResponseEntity.class);
        Assertions.assertEquals(response.getBody(), CursoDTO.class);
    }

    @AfterEach
    void destruidorDeCenariosDeTeste() {

    }
}