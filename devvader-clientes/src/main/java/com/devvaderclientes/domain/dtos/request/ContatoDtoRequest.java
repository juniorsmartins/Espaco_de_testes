package com.devvaderclientes.domain.dtos.request;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Builder
@Getter
public final class ContatoDtoRequest {

    @NotBlank
    @Length(max = 15)
    private String fone;

    @Email
    @NotBlank
    @Length(max = 150)
    private String email;
}
