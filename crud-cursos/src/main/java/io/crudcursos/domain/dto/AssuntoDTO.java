package io.crudcursos.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
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

    @NotBlank
    @Length(max = 75)
    private String tema;
}
