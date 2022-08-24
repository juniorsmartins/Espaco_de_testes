package com.devvadernoticias.domain.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public final class NoticiaDtoIn {

    @NotBlank
    @Length(min = 2, max = 25)
    private String chapeu;

    @NotBlank
    @Length(min = 15, max = 85)
    private String titulo;

    @NotBlank
    @Length(min = 50, max = 170)
    private String linhaFina;

    @NotBlank
    private String texto;

    @NotNull
    @Positive
    private Long idCliente;
}
