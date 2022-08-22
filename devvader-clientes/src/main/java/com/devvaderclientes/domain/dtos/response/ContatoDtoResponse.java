package com.devvaderclientes.domain.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ContatoDtoResponse {

    private Long contatoId;
    private String fone;
    private String email;
}
