package io.crudcursos.domain.controller;

import io.crudcursos.domain.dto.AssuntoDTO;
import io.crudcursos.domain.dto.CursoDTO;
import io.crudcursos.domain.entity.AssuntoEntity;
import io.crudcursos.domain.entity.CursoEntity;
import io.crudcursos.domain.entity.filtros.CursoFiltro;
import io.crudcursos.domain.repository.AssuntoRepository;
import io.crudcursos.domain.repository.CursoRepository;
import org.checkerframework.checker.nullness.Opt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

    private AssuntoEntity assuntoSalvo1;
    private AssuntoEntity assuntoSalvo3;
    private AssuntoEntity assuntoSalvo4;
    private CursoDTO cursoDTO1;
    private CursoDTO cursoDTO4;
    private CursoEntity cursoSalvo1;
    private CursoEntity cursoSalvo3;
    private CursoEntity cursoSalvo4;
    private CursoEntity cursoSalvo5;

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

        assuntoSalvo3 = AssuntoEntity.builder()
                .id(2L)
                .tema("Clipper")
                .build();

        cursoSalvo3 = CursoEntity.builder()
                .id(2L)
                .titulo("Clipper: programação estruturada.")
                .instituicao("DevClipper")
                .cargaHoraria(25)
                .dataConclusao(LocalDate.of(2020, 12, 12))
                .preco(BigDecimal.valueOf(125.68))
                .link("htp1")
                .assunto(assuntoSalvo3)
                .build();

        assuntoSalvo4 = AssuntoEntity.builder()
                .id(4L)
                .tema("Kotlin")
                .build();

        cursoSalvo4 = CursoEntity.builder()
                .id(4L)
                .titulo("Kotlin: Crud operacional com Java")
                .instituicao("Alura")
                .cargaHoraria(17L)
                .dataConclusao(LocalDate.of(2022, 10, 17))
                .preco(BigDecimal.valueOf(105.15))
                .link("http9")
                .assunto(assuntoSalvo4)
                .build();

        var assuntoDTO4 = AssuntoDTO.builder()
                .id(4L)
                .tema("Kotlin")
                .build();

        cursoDTO4 = CursoDTO.builder()
                .id(4L)
                .titulo("Kotlin: Crud operacional com Java")
                .instituicao("Alura")
                .cargaHoraria(17L)
                .dataConclusao(LocalDate.of(2022, 10, 17))
                .preco(BigDecimal.valueOf(105.15))
                .link("http9")
                .assunto(assuntoDTO4)
                .build();

        var assuntoSalvo5 = AssuntoEntity.builder()
                .id(4L)
                .tema("Java")
                .build();

        cursoSalvo5 = CursoEntity.builder()
                .id(5L)
                .titulo("APIRest com Java, Spring e PostgreSQL")
                .instituicao("Alura")
                .cargaHoraria(22L)
                .dataConclusao(LocalDate.of(2022, 11, 7))
                .preco(BigDecimal.valueOf(125.15))
                .link("http7")
                .assunto(assuntoSalvo5)
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
    void teste3_retornarResponseEntityComDtoAndHttp200QuandoConsultarPorId() {
        Mockito.when(this.cursoRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(cursoSalvo3));
        var response = this.controller.consultarPorId(cursoSalvo3.getId());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(CursoDTO.class, response.getBody().getClass());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(cursoSalvo3.getId(), response.getBody().getId());
    }

    @Test
    void teste4_retornarResponseEntityComDtoAndHttp200QuandoAtualizar() {
        Mockito.when(this.cursoRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(cursoSalvo4));
        Mockito.when(this.assuntoRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(assuntoSalvo4));
        Mockito.when(this.cursoRepository.saveAndFlush(Mockito.any())).thenReturn(cursoSalvo4);
        var response = this.controller.atualizar(4L, cursoDTO4);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(CursoDTO.class, response.getBody().getClass());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(cursoDTO4.getId(), response.getBody().getId());
        Mockito.verify(this.cursoRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(this.cursoRepository, Mockito.times(1)).saveAndFlush(Mockito.any());
        Mockito.verify(this.assuntoRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    void teste5_retornarResponseEntityComHttp200QuandoDeletarPorId() {
        Mockito.when(this.cursoRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(cursoSalvo5));
        Mockito.doNothing().when(this.cursoRepository).deleteById(Mockito.anyLong());
        var response = this.controller.deletarPorId(cursoSalvo5.getId());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Mockito.verify(this.cursoRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(this.cursoRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }
}