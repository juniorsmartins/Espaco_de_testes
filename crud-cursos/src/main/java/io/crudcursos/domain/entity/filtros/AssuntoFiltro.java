package io.crudcursos.domain.entity.filtros;

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
public final class AssuntoFiltro implements Serializable, IFiltro<Long> {

    public static final long serialVersionUID = 1L;

    private Long id;
    private String assunto;
}
