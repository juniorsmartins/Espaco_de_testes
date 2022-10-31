package io.crudcursos.domain.entity.filtros;

import io.crudcursos.domain.entity.CursoEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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
