package com.devvadercursos.entities;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class Curso implements Serializable, Entidade<Long> {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Long clienteId;
}
