package io.crudcursos.domain.entity.filtros;

import io.crudcursos.domain.entity.AssuntoEntity;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class CursoFiltro implements Serializable, IFiltro<Long> {

    public static final long serialVersionUID = 1L;

    private Long id;
    private String titulo;
    private String instituicao;
    private LocalDate dataConclusao;
    private float cargaHoraria;
    private BigDecimal preco;
    private String link;
    private AssuntoEntity assunto;
}
