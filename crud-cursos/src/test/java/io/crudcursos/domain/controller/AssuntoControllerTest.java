package io.crudcursos.domain.controller;

import io.crudcursos.domain.dto.AssuntoDTO;
import io.crudcursos.domain.entity.AssuntoEntity;
import io.crudcursos.domain.entity.filtros.AssuntoFiltro;
import io.crudcursos.domain.repository.AssuntoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@SpringBootTest
class AssuntoControllerTest {

    private AssuntoDTO assuntoDTO1;
    private AssuntoEntity assuntoEntity1;
    private AssuntoEntity assuntoEntity3;
    private AssuntoEntity assuntoEntity4;
    private AssuntoEntity assuntoEntity42;
    private AssuntoEntity assuntoEntity5;

    @Autowired
    private AController<AssuntoDTO, AssuntoFiltro, Long> controller;

    @MockBean
    private AssuntoRepository assuntoRepository;

    @BeforeEach
    void criadorDeCenariosParaTeste() {
        assuntoEntity1 = AssuntoEntity.builder()
                .id(1L)
                .tema("Python")
                .build();

        assuntoDTO1 = AssuntoDTO.builder()
                .tema("Python")
                .build();

        assuntoEntity3 = AssuntoEntity.builder()
                .id(3L)
                .tema("C++")
                .build();

        assuntoEntity4 = AssuntoEntity.builder()
                .id(4L)
                .tema("Golang")
                .build();

        assuntoEntity42 = AssuntoEntity.builder()
                .id(4L)
                .tema("Golang Atual")
                .build();

        assuntoEntity5 = AssuntoEntity.builder()
                .id(5L)
                .tema("Golang Atual")
                .build();
    }

    @Test
    void teste1_retornarResponseEntityComDtoAndHTTP201QuandoCadastrar() {
        Mockito.when(this.assuntoRepository.saveAndFlush(Mockito.any())).thenReturn(assuntoEntity1);
        var response = controller.criar(assuntoDTO1);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(AssuntoDTO.class, response.getBody().getClass());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getBody().getId());
        Assertions.assertEquals(assuntoDTO1.getTema(), response.getBody().getTema());
    }

    //    @Test
//    void teste2_retornarResponseEntityComPageDeAssuntoDTOAndHttp200QuandoBuscarTodosComPaginacaoAndFiltro() {
//        var assunto1 = this.assuntoRepository.save(AssuntoEntity.builder().tema("Scala").build());
//        var assunto2 = this.assuntoRepository.save(AssuntoEntity.builder().tema("TypeScript").build());
//        var assunto3 = this.assuntoRepository.save(AssuntoEntity.builder().tema("Kotlin").build());
//
//        var response = this.controller.buscarTodos(AssuntoFiltro.builder()
//                .tema("TypeScript").build(), Pageable.ofSize(5));
//
//        Assertions.assertNotNull(response);
//        Assertions.assertEquals(ResponseEntity.class, response.getClass());
//        Assertions.assertNotNull(response.getBody());
//        Assertions.assertEquals(PageImpl.class, response.getBody().getClass());
//        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
//        Assertions.assertEquals(AssuntoDTO.class, response.getBody().stream().findFirst().get().getClass());
//        Assertions.assertEquals(assunto2.getTema(), response.getBody().stream().findFirst().get().getTema());
//
//        this.assuntoRepository.deleteById(assunto1.getId());
//        this.assuntoRepository.deleteById(assunto2.getId());
//        this.assuntoRepository.deleteById(assunto3.getId());
//    }

    @Test
    void teste3_retornarResponseEntityComDTOAndHttp200QuandoConsultarPorId() {
        Mockito.when(this.assuntoRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(assuntoEntity3));
        var response = this.controller.consultarPorId(assuntoEntity3.getId());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(AssuntoDTO.class, response.getBody().getClass());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody().getId());
        Assertions.assertEquals(assuntoEntity3.getTema(), response.getBody().getTema());
    }

    @Test
    void teste4_retornarResponseEntityComDtoAndHttp200QuandoAtualizar() {
        Mockito.when(this.assuntoRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(assuntoEntity4));
        Mockito.when(this.assuntoRepository.saveAndFlush(Mockito.any())).thenReturn(assuntoEntity42);
        var response = this.controller.atualizar(
                assuntoEntity4.getId(), AssuntoDTO.builder().tema("Golang Atual").build());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(AssuntoDTO.class, response.getBody().getClass());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(assuntoEntity4.getId(), response.getBody().getId());
        Assertions.assertNotEquals(assuntoEntity4.getTema(), response.getBody().getTema());
    }

    @Test
    void teste5_retornarResponseEntityComHttp200QuandoDeletarPorId() {
        Mockito.when(this.assuntoRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(assuntoEntity5));
        Mockito.doNothing().when(this.assuntoRepository).deleteById(Mockito.anyLong());
        var response = this.controller.deletarPorId(assuntoEntity5.getId());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Mockito.verify(this.assuntoRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(this.assuntoRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }
}