package com.devvaderclientes.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "enderecos")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class EnderecoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "endereco_id")
    private Long enderecoId;

    @Column(name = "cep", nullable = false)
    private String cep;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "cidade", nullable = false)
    private String cidade;

    @Column(name = "bairro", nullable = false)
    private String bairro;

    @Column(name = "logradouro", nullable = false)
    private String logradouro;

    @Column(name = "numero")
    private int numero;

    @Column(name = "complemento")
    private String complemento;

    @OneToOne
    @JoinColumn(name = "id_cliente")
    private ClienteEntity cliente;
}
