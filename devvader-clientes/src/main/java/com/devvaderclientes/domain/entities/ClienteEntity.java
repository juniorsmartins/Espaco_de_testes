package com.devvaderclientes.domain.entities;

import com.devvaderclientes.domain.entities.enuns.EscolaridadeEnum;
import com.devvaderclientes.domain.entities.enuns.SexoEnum;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Clientes")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class ClienteEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private Long clienteId;

    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Column(name = "sobrenome", length = 100, nullable = false)
    private String sobrenome;

    @Column(name = "cpf", length = 14, nullable = false, unique = true)
    private String cpf;

    @Column(name = "data_nascimento", nullable = false)
    private String dataNascimento;

    @Column(name = "sexo", nullable = false)
    @Enumerated(EnumType.STRING)
    private SexoEnum sexo;

    @Column(name = "escolaridade", nullable = false)
    @Enumerated(EnumType.STRING)
    private EscolaridadeEnum escolaridade;

    @OneToMany(mappedBy = "cliente", cascade = {CascadeType.ALL}, orphanRemoval = true,
            fetch = FetchType.EAGER, targetEntity = ContatoEntity.class)
    private List<ContatoEntity> contatos;

    @OneToOne(mappedBy = "cliente", cascade = {CascadeType.ALL}, orphanRemoval = true,
            fetch = FetchType.EAGER, targetEntity = EnderecoEntity.class)
    private EnderecoEntity endereco;
}
