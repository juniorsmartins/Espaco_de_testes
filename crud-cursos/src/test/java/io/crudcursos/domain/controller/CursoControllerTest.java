package io.crudcursos.domain.controller;

import io.crudcursos.domain.dto.AssuntoDTO;
import io.crudcursos.domain.dto.CursoDTO;
import io.crudcursos.domain.entity.AssuntoEntity;
import io.crudcursos.domain.entity.filtros.CursoFiltro;
import io.crudcursos.domain.repository.AssuntoRepository;
import io.crudcursos.domain.repository.CursoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CursoControllerTest {

    private CursoDTO cursoDTO1;
    private AssuntoEntity assuntoSalvo1;

    @Autowired
    private AController<CursoDTO, CursoFiltro, Long> controller;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private AssuntoRepository assuntoRepository;

    @BeforeEach
    void criadorDeCenariosParaTeste() {
        assuntoSalvo1 = this.assuntoRepository.save(AssuntoEntity.builder()
                .assunto("C++")
                .build());

        cursoDTO1 = CursoDTO.builder()
                .titulo("C++: microsserviços com RabbitMQ")
                .instituicao("Alura")
                .cargaHoraria(28)
                .dataConclusao(LocalDate.of(2022, 10, 17))
                .preco(BigDecimal.valueOf(215.17))
                .link("http1")
                .build();
    }

    @Test
    void teste1_retornarResponseEntityComDtoAndHttp201QuandoCadastrar() {
        cursoDTO1.setAssunto(AssuntoDTO.builder().id(assuntoSalvo1.getId()).build());
        var response = this.controller.criar(cursoDTO1);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(CursoDTO.class, response.getBody().getClass());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(cursoDTO1.getTitulo(), response.getBody().getTitulo());

        this.cursoRepository.deleteById(response.getBody().getId());
        this.assuntoRepository.delete(assuntoSalvo1);
    }
}