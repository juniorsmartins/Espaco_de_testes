package com.devvadercursos.application_business.usecases.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public final class CursoDTOIn extends DTOIn<Long> implements Serializable {

    private String titulo;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Long clienteId;
}
