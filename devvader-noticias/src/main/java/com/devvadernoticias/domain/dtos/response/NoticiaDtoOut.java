package com.devvadernoticias.domain.dtos.response;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class NoticiaDtoOut {

    private Long noticiaId;
    private String chapeu;
    private String titulo;
    private String linhaFina;
    private String texto;
    private Long cliente;
}
