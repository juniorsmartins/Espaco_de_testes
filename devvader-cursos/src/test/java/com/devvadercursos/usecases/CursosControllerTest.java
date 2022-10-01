package com.devvadercursos.usecases;

import com.devvadercursos.application_business.usecases.dtos.CursoDTO;
import com.devvadercursos.interface_adapters.controllers.CursosControllerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

@SpringBootTest
class CursosControllerTest {

    private static final String TITULO_1 = "Java Avançado III";
    private static final String TITULO_2 = "Java Avançado IV";
    private static final String TITULO_3 = "Java Avançado V";

    @Autowired
    private CursosControllerImpl cursosControllerImpl;

    private CursoDTO cursoDTO1;
    private ResponseEntity<CursoDTO> response1;
    private CursoDTO cursoDTO2;
    private CursoDTO cursoDTO3;

    @BeforeEach
    void criadorDeCenariosDeTeste() {

        // Teste01
        cursoDTO1 = CursoDTO.builder()
                .titulo(TITULO_1)
                .descricao("Microserviços, API RestFul e RabbitMQ")
                .dataInicio(LocalDate.of(2022, 05, 01))
                .dataFim(LocalDate.of(2022, 06, 02))
                .cliente(1001L)
                .build();

        // Teste02
        cursoDTO2 = CursoDTO.builder()
                .titulo(TITULO_2)
                .descricao("Microserviços, API RestFul e Spring Cloud")
                .dataInicio(LocalDate.of(2022, 06, 10))
                .dataFim(LocalDate.of(2022, 07, 22))
                .cliente(1002L)
                .build();

        // Teste03
        cursoDTO3 = CursoDTO.builder()
                .titulo(TITULO_3)
                .descricao("Microserviços, API RestFul e Spring Cloud")
                .dataInicio(LocalDate.of(2022, 8, 15))
                .dataFim(LocalDate.of(2022, 10, 25))
                .cliente(1003L)
                .build();
    }

    @Test
    void teste1_retornar201Quando_cadastrar() {
        response1 = cursosControllerImpl.cadastrar(cursoDTO1);

        Assertions.assertNotNull(response1);
        Assertions.assertEquals(ResponseEntity.class, response1.getClass());
        Assertions.assertNotNull(response1.getBody());
        Assertions.assertEquals(CursoDTO.class, (response1.getBody()).getClass());
        Assertions.assertEquals(TITULO_1, response1.getBody().getTitulo());
        Assertions.assertEquals(HttpStatus.CREATED, response1.getStatusCode());

        cursosControllerImpl.deletar(response1.getBody().getId());
    }

    @Test
    void teste2_retornar200Quando_atualizarTotalOuSalvar() {
        var response = cursosControllerImpl.cadastrar(cursoDTO1);
        var responseAtualizar = cursosControllerImpl.atualizarTotalOuSalvar(response.getBody().getId(), cursoDTO3);

        Assertions.assertNotNull(responseAtualizar);
        Assertions.assertEquals(ResponseEntity.class, responseAtualizar.getClass());
        Assertions.assertNotNull(responseAtualizar.getBody());
        Assertions.assertEquals(CursoDTO.class, responseAtualizar.getBody().getClass());
        Assertions.assertEquals(TITULO_3, responseAtualizar.getBody().getTitulo());
        Assertions.assertEquals(HttpStatus.OK, responseAtualizar.getStatusCode());

        cursosControllerImpl.deletar(response.getBody().getId());
    }

    @Test
    void teste4_retornar200Quando_deletar() {
        var response = cursosControllerImpl.cadastrar(cursoDTO2);
        var responseDelete = cursosControllerImpl.deletar(response.getBody().getId());

        Assertions.assertNotNull(responseDelete);
        Assertions.assertEquals(ResponseEntity.class, responseDelete.getClass());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(String.class, responseDelete.getBody().getClass());
        Assertions.assertEquals("DELETADO!", responseDelete.getBody());
        Assertions.assertEquals(HttpStatus.OK, responseDelete.getStatusCode());
    }
}