package com.devvadercursos.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "registros")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public final class Registro extends Entidade<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "link_certificado")
    private String linkCertificado;

    @Column(name = "numero_certificado")
    private String numeroCertificado;

    @Column(name = "curso_id")
    private Long cursoId;
}
