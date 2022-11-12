package io.crudcursos.domain.cursos_context.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.crudcursos.domain.cursos_context.entity.CursoEntity;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class CursoDTOResponse implements Serializable, IDTO<Long> {

    public static final long serialVersionUID = 1L;

    private Long id;
    private String titulo;
    private String instituicao;
    private float cargaHoraria;
    private LocalDate dataConclusao;
    private BigDecimal preco;
    private String link;
    private AssuntoDTOResponse assunto;

    public CursoDTOResponse(CursoEntity cursoEntity) {
        this.id = cursoEntity.getId();
        this.titulo = cursoEntity.getTitulo();
        this.instituicao = cursoEntity.getInstituicao();
        this.cargaHoraria = cursoEntity.getCargaHoraria();
        this.dataConclusao = cursoEntity.getDataConclusao();
        this.preco = cursoEntity.getPreco();
        this.link = cursoEntity.getLink();
        this.assunto = new AssuntoDTOResponse(cursoEntity.getAssunto());
    }
}
