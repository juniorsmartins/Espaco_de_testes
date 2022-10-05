package com.devvadercursos.application_business.usecases.excecoes;

import lombok.Getter;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class LogErrorsDTO {

    private List<String> errors;

    public LogErrorsDTO(BindingResult resultado) {
        this.errors = new ArrayList<>();
        resultado.getAllErrors().forEach(error -> this.errors.add(error.getDefaultMessage()));
    }

    public LogErrorsDTO(RuntimeException except) {
        this.errors = Arrays.asList(except.getMessage());
    }
}
