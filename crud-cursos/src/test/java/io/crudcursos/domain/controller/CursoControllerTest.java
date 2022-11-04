package io.crudcursos.domain.controller;

import io.crudcursos.domain.dto.AssuntoDTO;
import io.crudcursos.domain.dto.CursoDTO;
import io.crudcursos.domain.entity.AssuntoEntity;
import io.crudcursos.domain.entity.CursoEntity;
import io.crudcursos.domain.entity.filtros.CursoFiltro;
import io.crudcursos.domain.repository.AssuntoRepository;
import io.crudcursos.domain.repository.CursoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
class CursoControllerTest {

    private CursoDTO cursoDTO1;
    private CursoEntity cursoSalvo1;
    private CursoEntity cursoSalvo2;
    private AssuntoEntity assuntoSalvo1;
    private AssuntoEntity assuntoSalvo2;

    @Autowired
    private AController<CursoDTO, CursoFiltro, Long> controller;

    @MockBean
    private CursoRepository cursoRepository;

    @MockBean
    private AssuntoRepository assuntoRepository;

    @BeforeEach
    void criadorDeCenariosParaTeste() {
        assuntoSalvo1 = AssuntoEntity.builder()
                .id(1L)
                .tema("Pascal")
                .build();

        cursoSalvo1 = CursoEntity.builder()
                .id(1L)
                .titulo("Pascar: flexibilidade com código macarrônico.")
                .instituicao("Pascoleti Academy")
                .cargaHoraria(25)
                .dataConclusao(LocalDate.of(2020, 12, 12))
                .preco(BigDecimal.valueOf(125.68))
                .link("htp1")
                .assunto(assuntoSalvo1)
                .build();

        cursoDTO1 = CursoDTO.builder()
                .id(1L)
                .titulo("Pascar: flexibilidade com código macarrônico.")
                .instituicao("Pascoleti Academy")
                .cargaHoraria(25)
                .dataConclusao(LocalDate.of(2020, 12, 12))
                .preco(BigDecimal.valueOf(125.68))
                .link("htp1")
                .assunto(AssuntoDTO.builder()
                        .id(1L)
                        .tema("Pascal")
                        .build())
                .build();

        assuntoSalvo2 = AssuntoEntity.builder()
                .id(2L)
                .tema("Clipper")
                .build();

        cursoSalvo2 = CursoEntity.builder()
                .id(2L)
                .titulo("Clipper: programação estruturada.")
                .instituicao("DevClipper")
                .cargaHoraria(25)
                .dataConclusao(LocalDate.of(2020, 12, 12))
                .preco(BigDecimal.valueOf(125.68))
                .link("htp1")
                .assunto(assuntoSalvo2)
                .build();
    }

    @Test
    void teste1_retornarResponseEntityComDtoAndHttp201QuandoCadastrar() {
        Mockito.when(this.assuntoRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(assuntoSalvo1));
        Mockito.when(this.cursoRepository.saveAndFlush(Mockito.any())).thenReturn(cursoSalvo1);
        var response = this.controller.criar(cursoDTO1);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(CursoDTO.class, response.getBody().getClass());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(cursoDTO1.getTitulo(), response.getBody().getTitulo());
    }

    @Test
    void teste2_retornarResponseEntityComDtoAndHttp200QuandoConsultarPorId() {
        Mockito.when(this.cursoRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(cursoSalvo2));
        var response = this.controller.consultarPorId(cursoSalvo2.getId());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(CursoDTO.class, response.getBody().getClass());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(cursoSalvo2.getId(), response.getBody().getId());
    }
}