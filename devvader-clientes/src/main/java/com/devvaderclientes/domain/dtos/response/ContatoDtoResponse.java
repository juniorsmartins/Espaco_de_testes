package com.devvaderclientes.domain.dtos.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public final class ContatoDtoResponse {

    private Long contatoId;
    private String fone;
    private String email;
}
