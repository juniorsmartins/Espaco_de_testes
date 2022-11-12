package io.crudcursos.domain.entity;

import io.crudcursos.domain.dto.CursoDTORequest;
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

    public CursoEntity(CursoDTORequest cursoDTORequest) {
        this.titulo = cursoDTORequest.titulo();
        this.instituicao = cursoDTORequest.instituicao();
        this.cargaHoraria = cursoDTORequest.cargaHoraria();
        this.dataConclusao = cursoDTORequest.dataConclusao();
        this.preco = cursoDTORequest.preco();
        this.link = cursoDTORequest.link();
        this.assunto = new AssuntoEntity(cursoDTORequest.assunto());
    }
}
