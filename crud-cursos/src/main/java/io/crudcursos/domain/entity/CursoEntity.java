package io.crudcursos.domain.entity;

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
@ToString
public final class CursoEntity implements Serializable {

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
}
