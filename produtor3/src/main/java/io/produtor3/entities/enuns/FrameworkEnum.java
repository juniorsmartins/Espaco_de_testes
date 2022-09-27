package io.produtor3.entities.enuns;

import lombok.Getter;

@Getter
public enum FrameworkEnum {

    SPRING("Spring"),
    JAVAEE("JavaEE"),
    JAKARTA("Jakarta"),
    ANGULAR("Angular");

    private String valor;

    FrameworkEnum(String valor) {
        this.valor = valor;
    }
}
