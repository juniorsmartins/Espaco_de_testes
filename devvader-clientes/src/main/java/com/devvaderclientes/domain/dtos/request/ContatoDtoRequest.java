package com.devvaderclientes.domain.dtos.request;

import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public final class ContatoDtoRequest {

    private Long contatoId;
    private String fone;
    private String email;
}
