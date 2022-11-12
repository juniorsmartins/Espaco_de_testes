package io.crudcursos.domain.cursos_context.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public record AssuntoDTORequest
        (
            Long id,
            @NotBlank
            @Length(max = 75)
            String tema
        ) implements Serializable, IDTO<Long>
{
    public static final long serialVersionUID = 1L;
}
