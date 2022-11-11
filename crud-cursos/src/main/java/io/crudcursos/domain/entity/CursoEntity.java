package io.crudcursos.domain.entity;

import io.crudcursos.domain.dto.AssuntoDTO;
import io.crudcursos.domain.dto.CursoDTO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cursos")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public final class CursoEntity implements Serializable, IEntity<Long> {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "instituicao")
    private String instituicao;

    @Column(name = "data_conclusao")
    private LocalDate dataConclusao;

    @Column(name = "carga_horaria")
    private float cargaHoraria;

    @Column(name = "preco")
    private BigDecimal preco;

    @Column(name = "link")
    private String link;

    @ManyToOne(targetEntity = AssuntoEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "assunto_id", updatable = true, nullable = false, unique = false)
    private AssuntoEntity assunto;

    public CursoEntity(CursoDTO cursoDTO) {
        this.titulo = cursoDTO.getTitulo();
        this.instituicao = cursoDTO.getInstituicao();
        this.cargaHoraria = cursoDTO.getCargaHoraria();
        this.dataConclusao = cursoDTO.getDataConclusao();
        this.preco = cursoDTO.getPreco();
        this.link = cursoDTO.getLink();
        this.assunto = new AssuntoEntity(cursoDTO.getAssunto());
    }
}
