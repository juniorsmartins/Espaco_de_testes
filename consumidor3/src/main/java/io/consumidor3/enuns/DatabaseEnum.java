package io.consumidor3.enuns;

import lombok.Getter;

@Getter
public enum DatabaseEnum {

    POSTGRESQL("PostgreSQL"),
    MARIADB("MariaDB"),
    MYSQL("MySQL"),
    ORACLE("Oracle"),
    MONGODB("MongoDB");

    private String valor;

    DatabaseEnum(String valor) {
        this.valor = valor;
    }
}
