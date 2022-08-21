package com.devvaderclientes.domain.dtos.response;

import com.devvaderclientes.domain.dtos.request.ContatoDtoRequest;
import com.devvaderclientes.domain.dtos.request.EnderecoDtoRequest;
import com.devvaderclientes.domain.entities.enuns.EscolaridadeEnum;
import com.devvaderclientes.domain.entities.enuns.SexoEnum;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public final class ClienteDtoResponse {

    private Long clienteId;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String dataNascimento;
    private SexoEnum sexo;
    private EscolaridadeEnum escolaridade;
    private List<ContatoDtoRequest> contatos;
    private EnderecoDtoRequest endereco;
}
