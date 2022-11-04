package io.crudcursos.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class AssuntoDTO implements Serializable, IDTO<Long> {

    public static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    @NotEmpty
    @Length(max = 75)
    private String tema;
}
