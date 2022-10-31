package io.crudcursos.domain.entity.filtros;

import lombok.*;

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
