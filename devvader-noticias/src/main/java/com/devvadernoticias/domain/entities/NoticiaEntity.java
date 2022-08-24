package com.devvadernoticias.domain.entities;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Entity
@Table(name = "noticias")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class NoticiaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticiaId;

    @Column(name = "chapeu", nullable = false, length = 25)
    private String chapeu;

    @Column(name = "titulo", nullable = false, length = 85)
    private String titulo;

    @Column(name = "tinha_fina", nullable = false, length = 170)
    private String linhaFina;

    @Column(name = "texto", nullable = false)
    private String texto;

    @Column(name = "id_cliente", nullable = false)
    private Long idCliente;
}
