package com.devvaderclientes.application.controllers;

import com.devvaderclientes.domain.dtos.request.ClienteDtoRequest;
import com.devvaderclientes.domain.dtos.request.ContatoDtoRequest;
import com.devvaderclientes.domain.dtos.request.EnderecoDtoRequest;
import com.devvaderclientes.domain.entities.ClienteEntity;
import com.devvaderclientes.domain.entities.ContatoEntity;
import com.devvaderclientes.domain.entities.EnderecoEntity;
import com.devvaderclientes.domain.entities.enuns.EscolaridadeEnum;
import com.devvaderclientes.domain.entities.enuns.SexoEnum;
import com.devvaderclientes.domain.ports.IClienteService;
import com.devvaderclientes.resources.repositories.IClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest
class ClienteControllerTest {

    public static final String FONE1 = "(80)99999-8548";
    public static final String EMAIL1 = "robert@gmail.com";
    public static final String FONE2 = "(75)99999-7575";
    public static final String EMAIL2 = "martin@yahoo.com";

    public static final String CEP1 = "58055-290";
    public static final String ESTADO1 = "PB";
    public static final String CIDADE1 = "João Pessoa";
    public static final String BAIRRO1 = "Mangabeira";
    public static final int NUMERO1 = 1840;
    public static final String LOGRADOURO1 = "Rua Comerciante Pedro Joaquim de Almeida";
    public static final String COMPLEMENTO1 = "Edifício Blanc, apto 301";

    public static final String NOME1 = "Robert";
    public static final String SOBRENOME1 = "Martins";
    public static final String DATA_NASCIMENTO1 = "17/06/1965";
    public static final String CPF1 = "858.174.630-67";

    @Autowired
    IClienteService iClienteService;

    @Mock
    IClienteRepository iClienteRepository;

    private ClienteEntity clienteEntity1;
    private ClienteDtoRequest clienteDtoRequest1;

    @BeforeEach
    void criadorDeCenariosParaTeste() {
        teste1();
    }

    @Test
    void teste1_retornarPositivoQuando_cadastrar() {
        Mockito.when(iClienteRepository.saveAndFlush(Mockito.any())).thenReturn(clienteEntity1);

        var response = iClienteService.cadastrar(clienteDtoRequest1);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class, response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ClienteDtoResponse.class, response.getBody());
    }


    void teste1() {
        var contatoEntity1 = ContatoEntity.builder()
                .contatoId(1L)
                .fone(FONE1)
                .email(EMAIL1)
                .build();

        var contatoEntity2 = ContatoEntity.builder()
                .contatoId(2L)
                .fone(FONE2)
                .email(EMAIL2)
                .build();

        var enderecoEntity1 = EnderecoEntity.builder()
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
                .clienteId(1L)
                .nome(NOME1)
                .sobrenome(SOBRENOME1)
                .cpf(CPF1)
                .sexo(SexoEnum.MASCULINO)
                .escolaridade(EscolaridadeEnum.DOUTORADO)
                .dataNascimento(DATA_NASCIMENTO1)
                .contatos(List.of(contatoEntity1, contatoEntity2))
                .endereco(enderecoEntity1)
                .build();

        var contatoDtoRequest1 = ContatoDtoRequest.builder()
                .fone(FONE1)
                .email(EMAIL1)
                .build();

        var contatoDtoRequest2 = ContatoDtoRequest.builder()
                .fone(FONE2)
                .email(EMAIL2)
                .build();

        var enderecoDtoRequest1 = EnderecoDtoRequest.builder()
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