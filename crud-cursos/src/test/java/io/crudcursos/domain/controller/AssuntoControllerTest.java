package io.crudcursos.domain.controller;

import io.crudcursos.domain.dto.AssuntoDTO;
import io.crudcursos.domain.dto.AssuntoDTOResponse;
import io.crudcursos.domain.entity.AssuntoEntity;
import io.crudcursos.domain.entity.filtros.AssuntoFiltro;
import io.crudcursos.domain.excecoes.ExcecoesDeBeanValidationTratadas;
import io.crudcursos.domain.excecoes.ExcecoesGeraisTratadas;
import io.crudcursos.domain.excecoes.MensagensPadrao;
import io.crudcursos.domain.excecoes.RecursoNaoEncontradoException;
import io.crudcursos.domain.repository.AssuntoRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
// @TestPropertySource(properties = {"spring.profiles.active=test"})
class AssuntoControllerTest {

    private static final Long ID_INEXISTENTE = 10101010101L;

    @Autowired
    private AController<AssuntoDTO, AssuntoDTOResponse, AssuntoFiltro, Long> controller;

    @MockBean
    private AssuntoRepository assuntoRepository;

//    @BeforeAll
//    void criadorDeCenariosParaTesteAcionadoUnicaVezAntesDeTodosOsTestes() {}
//
//    @BeforeEach
//    void criadorDeCenariosParaTesteAcionadoTodaVezAntesDeCadaTeste() {}
//
//    @AfterEach
//    void destruidorDeCenariosParaTesteAcionadoTodaVezAposCadaTeste() {}
//
//    @AfterAll
//    void destruidorDeCenariosParaTesteAcionadoUnicaVezDepoisDeTodosOsTestes() {}

    @Test
    void cadastrar_teste1_retornarResponseEntityComDtoAndHTTP201() {
        var assuntoEntity = AssuntoEntity.builder()
                .id(1L)
                .tema("Python")
                .build();
        Mockito.when(this.assuntoRepository.saveAndFlush(Mockito.any())).thenReturn(assuntoEntity);
        var assuntoDTO = AssuntoDTO.builder()
                .tema("Python")
                .build();
        var response = controller.criar(assuntoDTO);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(AssuntoDTOResponse.class, response.getBody().getClass());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getBody().id());
        Assertions.assertEquals(assuntoDTO.getTema(), response.getBody().tema());
        Mockito.verify(this.assuntoRepository, Mockito.times(1)).saveAndFlush(Mockito.any());
    }

    @Test
    void cadastrar_teste2_lancarExcecaoNullPointerException_quandoDTOForNull() {
        Throwable thrown = catchThrowable(() -> {
           this.controller.criar(null);
        });
        assertThat(thrown).isInstanceOf(NullPointerException.class);
        Mockito.verifyNoInteractions(this.assuntoRepository);
    }

//      @Test
//    void buscarTodos_teste1_retornarResponseEntityComPageDeAssuntoDTOAndHttp200_quandoBuscarTodosSemPaginacaoAndSemFiltro() {
//        var assuntoEntity1 = AssuntoEntity.builder().id(1L).tema("Scala").build();
//        var assuntoEntity2 = AssuntoEntity.builder().id(2L).tema("TypeScript").build();
//        var assuntoEntity3 = AssuntoEntity.builder().id(3L).tema("Kotlin").build();
//        Mockito.when(this.assuntoRepository.findAll(AssuntoFiltro.builder().build(), )).thenReturn();
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
    void consultarPorId_teste1_retornarResponseEntityComDTOAndHttp200() {
        var assuntoEntity = AssuntoEntity.builder()
                .id(3L)
                .tema("C++")
                .build();
        Mockito.when(this.assuntoRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(assuntoEntity));

        var response = this.controller.consultarPorId(assuntoEntity.getId());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(AssuntoDTO.class, response.getBody().getClass());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody().getId());
        Assertions.assertEquals(assuntoEntity.getTema(), response.getBody().getTema());
        Mockito.verify(this.assuntoRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    void consultarPorId_teste2_lancarExcecaoRecursoNaoEncontradoException_quandoIdNaoExistir() {
        Throwable thrown = catchThrowable(() -> {
            this.controller.consultarPorId(ID_INEXISTENTE);
        });
        assertThat(thrown).isInstanceOf(RecursoNaoEncontradoException.class)
                .hasMessage(MensagensPadrao.ASSUNTO_NAO_ENCONTRADO);
        Mockito.verify(this.assuntoRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    void atualizar_teste1_retornarResponseEntityComDtoAndHttp200() {
        var assuntoEntity = AssuntoEntity.builder()
                .id(4L)
                .tema("Golang")
                .build();
        Mockito.when(this.assuntoRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(assuntoEntity));

        var assuntoEntity2 = AssuntoEntity.builder()
                .id(4L)
                .tema("Golang + Atual")
                .build();
        Mockito.when(this.assuntoRepository.saveAndFlush(Mockito.any())).thenReturn(assuntoEntity2);

        var response = this.controller.atualizar(
                assuntoEntity2.getId(), AssuntoDTO.builder().tema("Golang + Atual").build());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(AssuntoDTO.class, response.getBody().getClass());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(assuntoEntity.getId(), response.getBody().getId());
        Assertions.assertEquals(assuntoEntity2.getId(), response.getBody().getId());
        Assertions.assertNotEquals(assuntoEntity.getTema(), response.getBody().getTema());
        Assertions.assertEquals(assuntoEntity2.getTema(), response.getBody().getTema());
        Mockito.verify(this.assuntoRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(this.assuntoRepository, Mockito.times(1)).saveAndFlush(Mockito.any());
    }

    @Test
    void atualizar_teste2_lancarExcecaoRecursoNaoEncontradoException_quandoIdNaoExistir() {
        Throwable thrown = catchThrowable(() -> {
            this.controller.atualizar(ID_INEXISTENTE, AssuntoDTO.builder().tema("Teste2 de Atualizar").build());
        });
        assertThat(thrown).isInstanceOf(RecursoNaoEncontradoException.class).hasMessage(MensagensPadrao.ASSUNTO_NAO_ENCONTRADO);
        Mockito.verify(this.assuntoRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(this.assuntoRepository, Mockito.times(0)).saveAndFlush(Mockito.any());
    }

    @Test
    void deletarPorId_teste1_retornarResponseEntityComHttp200() {
        var assuntoEntity = AssuntoEntity.builder()
                .id(5L)
                .tema("Golang Atual")
                .build();
        Mockito.when(this.assuntoRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(assuntoEntity));
        Mockito.doNothing().when(this.assuntoRepository).deleteById(Mockito.anyLong());

        var response = this.controller.deletarPorId(assuntoEntity.getId());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(String.class, response.getBody().getClass());
        Assertions.assertEquals(MensagensPadrao.ASSUNTO_DELETADO, response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Mockito.verify(this.assuntoRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(this.assuntoRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }

    @Test
    void deletarPorId_teste2_lancarExcecaoRecursoNaoEncontradoException() {
        Throwable thrown = catchThrowable(() -> {
           this.controller.deletarPorId(ID_INEXISTENTE);
        });
        assertThat(thrown).isInstanceOf(RecursoNaoEncontradoException.class).hasMessage(MensagensPadrao.ASSUNTO_NAO_ENCONTRADO);
        Mockito.verify(this.assuntoRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(this.assuntoRepository, Mockito.times(0)).deleteById(Mockito.anyLong());
    }
}