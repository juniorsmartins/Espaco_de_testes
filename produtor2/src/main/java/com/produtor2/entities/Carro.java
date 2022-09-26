package com.produtor2.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.produtor2.dto.CarroDTO;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "carros")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public final class Carro {

    private Long id;
    private String marca;
    private String cor;
    private int ano;

    public Carro(CarroDTO carroDTO) {
        this.marca = carroDTO.getMarca();
        this.cor = carroDTO.getCor();
        this.ano = carroDTO.getAno();
    }
}
