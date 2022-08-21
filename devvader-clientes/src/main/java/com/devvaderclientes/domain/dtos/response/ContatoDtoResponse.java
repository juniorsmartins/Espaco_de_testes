package com.devvaderclientes.domain.dtos.response;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class ContatoDtoResponse {

    private Long contatoId;
    private String fone;
    private String email;
}
