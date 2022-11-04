package io.crudcursos.domain.controller;

import io.crudcursos.domain.dto.AssuntoDTO;
import io.crudcursos.domain.entity.AssuntoEntity;
import io.crudcursos.domain.entity.filtros.AssuntoFiltro;
import io.crudcursos.domain.repository.AssuntoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class AssuntoControllerTest {

    private AssuntoDTO assuntoDTO1;
    private AssuntoEntity assuntoEntity1;
    private AssuntoEntity assuntoEntity2;

    @Autowired
    private AController<AssuntoDTO, AssuntoFiltro, Long> controller;

    @Autowired
    private AssuntoRepository assuntoRepository;

    @BeforeEach
    void criadorDeCenariosParaTeste() {
        assuntoDTO1 = AssuntoDTO.builder()
                .assunto("Java")
                .build();
        assuntoEntity1 = AssuntoEntity.builder()
                .assunto("Python")
                .build();
        assuntoEntity2 = AssuntoEntity.builder()
                .assunto("JavaScript")
                .build();
    }

    @Test
    void teste1_retornarResponseEntityComDtoAndHTTP201QuandoCadastrar() {
        var response = controller.criar(assuntoDTO1);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(AssuntoDTO.class, response.getBody().getClass());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getBody().getId());
        Assertions.assertEquals(assuntoDTO1.getAssunto(), response.getBody().getAssunto());

        this.assuntoRepository.deleteById(response.getBody().getId());
    }

    @Test
    void teste2_retornarResponseEntityComDTOAndHttp200QuandoConsultarPorId() {
        var assuntoSalvo = this.assuntoRepository.save(assuntoEntity1);
        var response = this.controller.consultarPorId(assuntoSalvo.getId());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(AssuntoDTO.class, response.getBody().getClass());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody().getId());
        Assertions.assertEquals(assuntoSalvo.getAssunto(), response.getBody().getAssunto());

        this.assuntoRepository.deleteById(assuntoSalvo.getId());
    }

    @Test
    void teste3_retornarResponseEntityComHttp200QuandoDeletarPorId() {
        var assuntoSalvo = this.assuntoRepository.save(assuntoEntity2);
        var response = this.controller.deletarPorId(assuntoSalvo.getId());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        var resultado = this.assuntoRepository.findById(assuntoSalvo.getId());
        Assertions.assertEquals(Boolean.TRUE, resultado.isEmpty());
    }

    @Test
    void teste4_retornarResponseEntityComDtoAndHttp200QuandoAtualizar() {
        var assunto1Salvo = this.assuntoRepository.save(assuntoEntity1);

        var response = this.controller.atualizar(
                assunto1Salvo.getId(), AssuntoDTO.builder().assunto("C#").build());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(AssuntoDTO.class, response.getBody().getClass());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(assunto1Salvo.getId(), response.getBody().getId());
        Assertions.assertNotEquals(assunto1Salvo.getAssunto(), response.getBody().getAssunto());

        this.assuntoRepository.deleteById(assunto1Salvo.getId());
    }

    @Test
    void teste5_retornarResponseEntityComPageDeAssuntoDTOAndHttp200QuandoBuscarTodosComPaginacaoAndFiltro() {
        var assunto1 = this.assuntoRepository.save(AssuntoEntity.builder().assunto("Scala").build());
        var assunto2 = this.assuntoRepository.save(AssuntoEntity.builder().assunto("TypeScript").build());
        var assunto3 = this.assuntoRepository.save(AssuntoEntity.builder().assunto("Kotlin").build());

        var response = this.controller.buscarTodos(AssuntoFiltro.builder()
                .assunto("TypeScript").build(), Pageable.ofSize(5));

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(PageImpl.class, response.getBody().getClass());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(AssuntoDTO.class, response.getBody().stream().findFirst().get().getClass());
        Assertions.assertEquals(assunto2.getAssunto(), response.getBody().stream().findFirst().get().getAssunto());

        this.assuntoRepository.deleteById(assunto1.getId());
        this.assuntoRepository.deleteById(assunto2.getId());
        this.assuntoRepository.deleteById(assunto3.getId());
    }
}