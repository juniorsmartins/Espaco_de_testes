package com.devvaderclientes.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "contatos")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class ContatoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contato_id")
    private Long contatoId;

    @Column(name = "fone")
    private String fone;

    @Column(name = "email", unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private ClienteEntity cliente;
}
