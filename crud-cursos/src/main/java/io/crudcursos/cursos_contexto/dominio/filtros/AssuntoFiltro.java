package io.crudcursos.cursos_contexto.dominio.filtros;

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
    private String tema;
}
