package com.produtor2.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.produtor2.entities.Carro;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class CarroDTO {

    private String marca;
    private String cor;
    private int ano;

    public CarroDTO(Carro carro) {
        this.marca = carro.getMarca();
        this.cor = carro.getCor();
        this.ano = carro.getAno();
    }
}
