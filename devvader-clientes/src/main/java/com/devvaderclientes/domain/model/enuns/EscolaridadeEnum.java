package com.devvaderclientes.domain.model.enuns;

import lombok.Getter;

@Getter
public enum EscolaridadeEnum {

    DOUTORADO("Doutorado"),
    MESTRADO_ACADEMICO("Mestrado Acadêmico"),
    MESTRADO_PROFISSIONAL("Mestrado Profissional"),
    ESPECIALIZACAO("Especialização"),
    SUPERIOR("Superior"),
    TECNICO("Técnico"),
    ENSINO_MEDIO("Ensino Médio"),
    ENSINO_FUNDAMENTAL("Ensino Fundamental"),
    FUNDAMENTAL_INCOMPLETO("Fundamental Incompleto");

    private String valor;

    EscolaridadeEnum(String valor) {
        this.valor = valor;
    }
}
