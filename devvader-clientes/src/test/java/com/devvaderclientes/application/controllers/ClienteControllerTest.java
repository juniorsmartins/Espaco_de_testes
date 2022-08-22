package com.devvaderclientes.application.controllers;

import com.devvaderclientes.domain.dtos.request.ClienteDtoRequest;
import com.devvaderclientes.domain.dtos.request.ContatoDtoRequest;
import com.devvaderclientes.domain.dtos.request.EnderecoDtoRequest;
import com.devvaderclientes.domain.dtos.response.ClienteDtoResponse;
import com.devvaderclientes.domain.entities.ClienteEntity;
import com.devvaderclientes.domain.entities.ContatoEntity;
import com.devvaderclientes.domain.entities.EnderecoEntity;
import com.devvaderclientes.domain.entities.enuns.EscolaridadeEnum;
import com.devvaderclientes.domain.entities.enuns.SexoEnum;
import com.devvaderclientes.resources.repositories.IClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ClienteControllerTest {

    static final String FONE1 = "(80)99999-8548";
    static final String EMAIL1 = "robert@gmail.com";
    static final String FONE2 = "(75)99999-7575";
    static final String EMAIL2 = "martin@yahoo.com";

    static final String CEP1 = "58055-290";
    static final String ESTADO1 = "PB";
    static final String CIDADE1 = "João Pessoa";
    static final String BAIRRO1 = "Mangabeira";
    static final String LOGRADOURO1 = "Rua Comerciante Pedro Joaquim de Almeida";
    static final int NUMERO1 = 1840;
    static final String COMPLEMENTO1 = "Edifício Blanc, apto 301";

    static final long CLIENTE_ID = 1L;
    static final String NOME1 = "Robert";
    static final String SOBRENOME1 = "Martins";
    static final String DATA_NASCIMENTO1 = "17/06/1965";
    static final String CPF1 = "858.174.630-67";

    @Autowired
    ClienteController clienteController;

    @Mock
    IClienteRepository iClienteRepository;

    private ClienteEntity clienteEntity1;
    private ClienteEntity clienteEntity2;
    private ContatoEntity contatoEntity1;
    private EnderecoEntity enderecoEntity1;
    private ClienteDtoRequest clienteDtoRequest1;
    private ClienteDtoRequest clienteDtoRequest2;
    private ContatoDtoRequest contatoDtoRequest1;
    private EnderecoDtoRequest enderecoDtoRequest1;

    @BeforeEach
    void criadorDeCenariosParaTeste() {
        teste1();
        teste2();
    }

    @Test
    void teste1_retornarPositivoQuando_cadastrar() {
        Mockito.when(iClienteRepository.saveAndFlush(Mockito.any())).thenReturn(clienteEntity1);

        var response = clienteController.cadastrar(clienteDtoRequest1);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ClienteDtoResponse.class, response.getBody().getClass());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(NOME1, ((ClienteDtoResponse) response.getBody()).getNome());
    }

    @Test
    void teste2_retornarPositivoQuando_lerTodos() {
        Mockito.when(iClienteRepository.findAll()).thenReturn(List.of(clienteEntity1, clienteEntity2));

        var response = clienteController.lerTodos();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ClienteDtoResponse.class, ((List<ClienteDtoResponse>) response.getBody()).get(0).getClass());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void teste3_retornarPositivoQuando_consultarPorId() {
        Mockito.when(iClienteRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(clienteEntity1));

        var response = clienteController.consultarPorId(CLIENTE_ID);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ClienteDtoResponse.class, response.getBody().getClass());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(CLIENTE_ID, ((ClienteDtoResponse) response.getBody()).getClienteId());
    }

    void teste2() {
        clienteEntity2 = ClienteEntity.builder()
                .clienteId(CLIENTE_ID)
                .nome(NOME1)
                .sobrenome(SOBRENOME1)
                .cpf(CPF1)
                .sexo(SexoEnum.MASCULINO)
                .escolaridade(EscolaridadeEnum.DOUTORADO)
                .dataNascimento(DATA_NASCIMENTO1)
                .contatos(List.of(contatoEntity1))
                .endereco(enderecoEntity1)
                .build();
    }

    void teste1() {
        contatoEntity1 = ContatoEntity.builder()
                .contatoId(1L)
                .fone(FONE1)
                .email(EMAIL1)
                .build();

        var contatoEntity2 = ContatoEntity.builder()
                .contatoId(2L)
                .fone(FONE2)
                .email(EMAIL2)
                .build();

        enderecoEntity1 = EnderecoEntity.builder()
                .enderecoId(1L)
                .cep(CEP1)
                .estado(ESTADO1)
                .cidade(CIDADE1)
                .bairro(BAIRRO1)
                .numero(NUMERO1)
                .logradouro(LOGRADOURO1)
                .complemento(COMPLEMENTO1)
                .build();

        clienteEntity1 = ClienteEntity.builder()
                .clienteId(CLIENTE_ID)
                .nome(NOME1)
                .sobrenome(SOBRENOME1)
                .cpf(CPF1)
                .sexo(SexoEnum.MASCULINO)
                .escolaridade(EscolaridadeEnum.DOUTORADO)
                .dataNascimento(DATA_NASCIMENTO1)
                .contatos(List.of(contatoEntity1, contatoEntity2))
                .endereco(enderecoEntity1)
                .build();

        contatoDtoRequest1 = ContatoDtoRequest.builder()
                .fone(FONE1)
                .email(EMAIL1)
                .build();

        var contatoDtoRequest2 = ContatoDtoRequest.builder()
                .fone(FONE2)
                .email(EMAIL2)
                .build();

        enderecoDtoRequest1 = EnderecoDtoRequest.builder()
                .cep(CEP1)
                .estado(ESTADO1)
                .cidade(CIDADE1)
                .bairro(BAIRRO1)
                .numero(NUMERO1)
                .logradouro(LOGRADOURO1)
                .complemento(COMPLEMENTO1)
                .build();

        clienteDtoRequest1 = ClienteDtoRequest.builder()
                .nome(NOME1)
                .sobrenome(SOBRENOME1)
                .cpf(CPF1)
                .sexo(SexoEnum.MASCULINO)
                .escolaridade(EscolaridadeEnum.DOUTORADO)
                .dataNascimento(DATA_NASCIMENTO1)
                .contatos(List.of(contatoDtoRequest1, contatoDtoRequest2))
                .endereco(enderecoDtoRequest1)
                .build();
    }

}

//    @Test
//    void teste2_retornarMethodArgumentNotValidExceptionQuando_cadastrarComNomeEmBranco() {
//
//        var response = clienteController.cadastrar(clienteDtoRequest2);
//        Assertions.assertNotNull(response);
//        Assertions.assertEquals(ResponseEntity.class, response.getClass());
//        Assertions.assertNotNull(response.getBody());
//        Assertions.assertEquals(List.class, response.getBody().getClass());
//
////        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//
////            Throwable thrown =  org.assertj.core.api.Assertions.catchThrowable(() -> clienteController
////                    .cadastrar(clienteDtoRequest2));
////        org.assertj.core.api.Assertions.assertThat(thrown).isInstanceOf(MethodArgumentNotValidException.class);
//    }