package com.devvaderclientes.domain.entities.enuns;

import lombok.Getter;

@Getter
public enum SexoEnum {

    MASCULINO("Masculino"),
    FEMININO("Feminino");

    private String valor;

    SexoEnum(String valor) {
        this.valor = valor;
    }
}
