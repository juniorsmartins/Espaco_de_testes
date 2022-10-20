package com.devvadercursos.application_business.usecases.mensageria;

import com.devvadercursos.enterprise_business.entities.enuns.TematicaEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public final class MensagemCurso {

    private Long id;
    private String titulo;
    private String descricao;
    private TematicaEnum tematica;
    private Long cliente;
}
