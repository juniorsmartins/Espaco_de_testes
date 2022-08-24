package com.devvadernoticias.application;

import com.devvadernoticias.domain.dtos.request.NoticiaDtoIn;
import com.devvadernoticias.domain.dtos.response.NoticiaDtoOut;
import com.devvadernoticias.domain.entities.NoticiaEntity;
import com.devvadernoticias.infra.INoticiaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class NoticiaControllerTest {

    public static final String CHAPEU = "Futebol Americano";
    public static final String TITULO = "Cuiabá Arsenal supera Sorriso Hornets nesse sábado";
    public static final String LINHA_FINA = "Quarterback fulano impôs placar com ampla vantagem no jogo aéreo";
    public static final String TEXTO = "LorenIpsun LorenIpsun LorenIpsun LorenIpsun LorenIpsun LorenIpsun LorenIpsun LorenIpsun";
    public static final long ID_CLIENTE = 1L;
    private NoticiaDtoIn noticiaDtoIn1;
    private NoticiaEntity noticiaEntity1;

    @Autowired
    private NoticiaController noticiaController;

    @Mock
    private INoticiaRepository iNoticiaRepository;

    @BeforeEach
    void criadorDeCenariosParaTeste() {
        teste1();
    }

    @Test
    void teste1_retornarPositivoQuando_criar() {
        Mockito.when(iNoticiaRepository.saveAndFlush(Mockito.any())).thenReturn(noticiaEntity1);

        var response = noticiaController.criar(noticiaDtoIn1);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(NoticiaDtoOut.class, response.getBody().getClass());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(ID_CLIENTE, ((NoticiaDtoOut) response.getBody()).getNoticiaId());
    }

    void teste1() {
        noticiaDtoIn1 = NoticiaDtoIn.builder()
                .chapeu(CHAPEU)
                .titulo(TITULO)
                .linhaFina(LINHA_FINA)
                .texto(TEXTO)
                .cliente(ID_CLIENTE)
                .build();

        noticiaEntity1 = NoticiaEntity.builder()
                .chapeu(CHAPEU)
                .titulo(TITULO)
                .linhaFina(LINHA_FINA)
                .texto(TEXTO)
                .cliente(ID_CLIENTE)
                .build();
    }
}