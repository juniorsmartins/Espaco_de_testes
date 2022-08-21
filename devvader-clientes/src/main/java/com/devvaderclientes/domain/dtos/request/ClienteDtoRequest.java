package com.devvaderclientes.domain.dtos.request;

import com.devvaderclientes.domain.entities.enuns.EscolaridadeEnum;
import com.devvaderclientes.domain.entities.enuns.SexoEnum;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public final class ClienteDtoRequest {

    private String nome;
    private String sobrenome;
    private String cpf;
    private String dataNascimento;
    private SexoEnum sexo;
    private EscolaridadeEnum escolaridade;
    private List<ContatoDtoRequest> contatos;
    private EnderecoDtoRequest endereco;
}
