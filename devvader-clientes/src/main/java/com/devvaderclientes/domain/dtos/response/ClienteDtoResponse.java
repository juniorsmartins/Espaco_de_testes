package com.devvaderclientes.domain.dtos.response;

import com.devvaderclientes.domain.entities.enuns.EscolaridadeEnum;
import com.devvaderclientes.domain.entities.enuns.SexoEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ClienteDtoResponse {

    private Long clienteId;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String dataNascimento;
    private SexoEnum sexo;
    private EscolaridadeEnum escolaridade;
    private List<ContatoDtoResponse> contatos;
    private EnderecoDtoResponse endereco;
}
