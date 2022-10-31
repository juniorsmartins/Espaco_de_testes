package io.crudcursos.domain.controller;

import io.crudcursos.domain.dto.AssuntoDTO;
import io.crudcursos.domain.entity.AssuntoEntity;
import io.crudcursos.domain.entity.filtros.AssuntoFiltro;
import io.crudcursos.domain.repository.AssuntoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class AssuntoControllerTest {

    private AssuntoDTO assuntoDTO1;
    private AssuntoEntity assuntoEntity1;

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
    }

    @Test
    void teste1_retornarResponseEntityComDtoSalvoAndHTTP201QuandoCadastrar() {
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
}