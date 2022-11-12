package io.crudcursos.aplicacao.controller;

import io.crudcursos.aplicacao.controller.AController;
import io.crudcursos.domain.dto.AssuntoDTORequest;
import io.crudcursos.domain.dto.CursoDTOResponse;
import io.crudcursos.domain.dto.CursoDTORequest;
import io.crudcursos.domain.entity.AssuntoEntity;
import io.crudcursos.domain.entity.CursoEntity;
import io.crudcursos.domain.entity.filtros.CursoFiltro;
import io.crudcursos.infra.repository.AssuntoRepository;
import io.crudcursos.infra.repository.CursoRepository;
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

    private AssuntoEntity assuntoSalvo3;
    private AssuntoEntity assuntoSalvo4;
    private CursoDTOResponse cursoDTOResponse4;
    private CursoEntity cursoSalvo3;
    private CursoEntity cursoSalvo4;
    private CursoEntity cursoSalvo5;

    @Autowired
    private AController<CursoDTORequest, CursoDTOResponse, CursoFiltro, Long> controller;

    @MockBean
    private CursoRepository cursoRepository;

    @MockBean
    private AssuntoRepository assuntoRepository;

    @BeforeEach
    void criadorDeCenariosParaTeste() {
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
    void cadastrar_teste1_retornarResponseEntityComDtoAndHttp201() {
        var assuntoSalvo = AssuntoEntity.builder()
                .id(1L)
                .tema("Pascal")
                .build();
        Mockito.when(this.assuntoRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(assuntoSalvo));

        var cursoSalvo = CursoEntity.builder()
                .id(1L)
                .titulo("Pascar: flexibilidade com código macarrônico.")
                .instituicao("Pascoleti Academy")
                .cargaHoraria(25)
                .dataConclusao(LocalDate.of(2020, 12, 12))
                .preco(BigDecimal.valueOf(125.68))
                .link("htp1")
                .assunto(assuntoSalvo)
                .build();
        Mockito.when(this.cursoRepository.saveAndFlush(Mockito.any())).thenReturn(cursoSalvo);

        var cursoDTORequest = new CursoDTORequest
                (
                1L, "Pascar: flexibilidade com código macarrônico.", "Pascoleti Academy",
                        25F, LocalDate.of(2020, 12, 12), BigDecimal.valueOf(125.68),
                        "htp1", new AssuntoDTORequest(1L, "Pascal")
                );
        var response = this.controller.criar(cursoDTORequest);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(CursoDTOResponse.class, response.getBody().getClass());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(cursoDTORequest.titulo(), response.getBody().getTitulo());
        Mockito.verify(this.assuntoRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(this.cursoRepository, Mockito.times(1)).saveAndFlush(Mockito.any());
    }

//    @Test
//    void teste2_retornarResponseEntityComPageDeDtosAndHttp200QuandoBuscarTodosSemPaginacaoAndSemFiltro() {
//        Faker faker = new Faker(new Locale("pt-BR"));
//
//        List<CursoEntity> listaDeCursosEntity = IntStream.rangeClosed(1, 5)
//                .mapToObj(curso ->
//                    CursoEntity.builder()
//                            .id(faker.number().randomNumber())
//                            .titulo(faker.name().title())
//                            .instituicao(faker.educator().university())
//                            .cargaHoraria(Float.parseFloat(faker.numerify("##")))
//                            .dataConclusao(LocalDate.of(2022, 5, 8))
//                            .preco(BigDecimal.valueOf(Long.parseLong(faker.numerify("##"))))
//                            .link(faker.internet().domainName())
//                            .assunto(AssuntoEntity.builder()
//                                    .id(faker.number().randomNumber())
//                                    .tema(faker.programmingLanguage().name())
//                                    .build())
//                            .build()
//                )
//                .collect(Collectors.toList());
//
//        Page<CursoEntity> paginaDeCursosEntity = new PageImpl<>(listaDeCursosEntity);
//
//        ExampleMatcher exampleMatcher = ExampleMatcher
//                .matchingAll()
//                .withIgnoreCase()
//                .withIgnoreNullValues()
//                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
//
//        Mockito.when(this.cursoRepository.findAll(
//                Example.of(CursoEntity.builder().build(), exampleMatcher),
//                Pageable.ofSize(10))).thenReturn(paginaDeCursosEntity);
//        var response = this.controller.buscarTodos(CursoFiltro.builder().build(), Pageable.unpaged());
//
//        Assertions.assertNotNull(response);
//        Assertions.assertEquals(ResponseEntity.class, response.getClass());
//        Assertions.assertNotNull(response.getBody());
//        Assertions.assertEquals(Page.class, response.getBody().getContent().getClass());
//        Assertions.assertNotNull(response.getBody().getContent().getClass());
//        Assertions.assertEquals(CursoDTOResponse.class, response.getBody().getContent().get(0).getClass());
//        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
//        Mockito.verify(this.cursoRepository, Mockito.times(1)).findAll(Mockito.any(), Pageable.unpaged());
//    }

    @Test
    void teste3_retornarResponseEntityComDtoAndHttp200QuandoConsultarPorId() {
        Mockito.when(this.cursoRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(cursoSalvo3));
        var response = this.controller.consultarPorId(cursoSalvo3.getId());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(CursoDTOResponse.class, response.getBody().getClass());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(cursoSalvo3.getId(), response.getBody().getId());
        Mockito.verify(this.cursoRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    void teste4_retornarResponseEntityComDtoAndHttp200QuandoAtualizar() {
        Mockito.when(this.cursoRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(cursoSalvo4));
        Mockito.when(this.assuntoRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(assuntoSalvo4));
        Mockito.when(this.cursoRepository.saveAndFlush(Mockito.any())).thenReturn(cursoSalvo4);

        var cursoDTORequest = new CursoDTORequest
            (
                4L, "Kotlin: Crud operacional com Java", "Alura", 17F,
                    LocalDate.of(2022, 10, 17), BigDecimal.valueOf(105.15),
                    "http9", new AssuntoDTORequest(4L, "Kotlin")
            );
        var response = this.controller.atualizar(4L, cursoDTORequest);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(CursoDTOResponse.class, response.getBody().getClass());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(cursoDTORequest.id(), response.getBody().getId());
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