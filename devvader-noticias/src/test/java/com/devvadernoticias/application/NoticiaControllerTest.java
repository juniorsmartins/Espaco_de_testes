package com.devvadernoticias.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NoticiaControllerTest {

    @Autowired
    private NoticiaController noticiaController;

    @Mock
    private INoticiaRepository iNoticiaRepository;

    @Test
    void teste1_retornarPositivoQuando_criar() {
        Mockito.when(iNoticiaRepository.saveAndFlush(Mockito.any())).thenReturn(noticiaEntity1);

        var response = noticiaController.criar(noticiaDtoIn1);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(NoticiaDtoOut.class, response.getBody().getClass());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}