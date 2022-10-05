package com.devvadercursos.enterprise_business.entities.enuns;

import lombok.Getter;

@Getter
public enum TematicaEnum {

    BACKEND("Back-end"),
    FRONTEND("Front-end"),
    DEVOPS("DevOps"),
    DBA("DBA"),
    BUSINESS("Business"),
    SOFTSKILLS("Soft Skills");

    private String valor;

    TematicaEnum(String valor) {
        this.valor = valor;
    }
}
