package com.devvadercursos.application_business.usecases.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class InstituicaoDTO implements IGenericsDTO<Long>, Serializable {

    private Long id;
    private String nome;
    private String site;
    private Long curso;
}
