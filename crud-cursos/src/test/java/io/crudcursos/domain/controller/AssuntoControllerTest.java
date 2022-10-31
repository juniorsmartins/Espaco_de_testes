package io.crudcursos.domain.controller;

import io.crudcursos.domain.dto.AssuntoDTO;
import io.crudcursos.domain.entity.filtros.AssuntoFiltro;
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

    @Autowired
    private IController<AssuntoDTO, AssuntoFiltro, Long> controller;

    @BeforeEach
    void criadorDeCenariosParaTeste() {
        assuntoDTO1 = AssuntoDTO.builder()
                .assunto("Java")
                .build();
    }

    @Test
    void teste1_retornar201QuandoCadastrar() {
        var response = controller.criar(assuntoDTO1);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class, response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(AssuntoDTO.class, (response.getBody()).getClass());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getBody().getId());
        Assertions.assertEquals(assuntoDTO1.getAssunto(), response.getBody().getAssunto());
    }
}