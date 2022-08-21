package com.devvaderclientes.domain.dtos.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public final class ContatoDtoRequest {

    private String fone;
    private String email;
}
