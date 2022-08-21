package com.devvaderclientes.domain.model.enuns;

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
