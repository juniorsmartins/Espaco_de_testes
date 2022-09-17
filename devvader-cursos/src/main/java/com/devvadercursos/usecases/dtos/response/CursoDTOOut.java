package com.devvadercursos.usecases.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class CursoDTOOut implements DTOOut<Long> {

    private Long id;
    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Long clienteId;
}
