package com.devvadercursos.enterprise_business.entities;

import com.devvadercursos.enterprise_business.entities.enuns.TematicaEnum;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "cursos")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public final class Curso implements IGenericsEntity<Long>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "titulo", length = 150)
    private String titulo;

    @Column(name = "descricao", length = 250)
    private String descricao;

    @Column(name = "tematica")
    @Enumerated(value = EnumType.STRING)
    private TematicaEnum tematica;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "data_hora_cadastro")
    private Instant dataHoraCadastro;

    @Column(name = "cliente_fk")
    private Long cliente;
}
