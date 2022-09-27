package io.produtor3.entities;

import io.produtor3.entities.enuns.DatabaseEnum;
import io.produtor3.entities.enuns.FrameworkEnum;
import io.produtor3.entities.enuns.LinguagemEnum;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "clientes")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public final class ProjetoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "linguagem", nullable = false)
    private LinguagemEnum linguagem;

    @Column(name = "framework", nullable = false)
    private FrameworkEnum framework;

    @Column(name = "database", nullable = false)
    private DatabaseEnum database;
}
