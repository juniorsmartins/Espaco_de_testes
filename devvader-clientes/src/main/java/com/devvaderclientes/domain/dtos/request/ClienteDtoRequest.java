package com.devvaderclientes.domain.dtos.request;

import com.devvaderclientes.domain.entities.enuns.EscolaridadeEnum;
import com.devvaderclientes.domain.entities.enuns.SexoEnum;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Getter
public final class ClienteDtoRequest {

    @NotBlank
    @Length(max = 50)
    private String nome;

    @NotBlank
    @Length(max = 100)
    private String sobrenome;

    @CPF
    @NotNull
    private String cpf;

    @NotBlank
    private String dataNascimento;

    @NotNull
    private SexoEnum sexo;

    @NotNull
    private EscolaridadeEnum escolaridade;

    @NotNull @Valid
    private List<ContatoDtoRequest> contatos;

    @NotNull @Valid
    private EnderecoDtoRequest endereco;
}
