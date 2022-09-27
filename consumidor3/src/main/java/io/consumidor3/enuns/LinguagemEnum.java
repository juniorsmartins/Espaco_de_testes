package io.consumidor3.enuns;

import lombok.Getter;

@Getter
public enum LinguagemEnum {

    JAVA("Java"),
    JAVASCRIPT("JavaScript"),
    TYPESCRIPT("TypeScript"),
    PYTHON("Python"),
    C1("C"),
    C2("C++"),
    C3("C#");

    private String valor;

    LinguagemEnum(String valor) {
        this.valor = valor;
    }
}
