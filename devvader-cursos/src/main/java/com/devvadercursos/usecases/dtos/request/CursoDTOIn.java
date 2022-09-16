package com.devvadercursos.usecases.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public final class CursoDTOIn implements DTOIn<Long> {

    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Long clienteId;
}
