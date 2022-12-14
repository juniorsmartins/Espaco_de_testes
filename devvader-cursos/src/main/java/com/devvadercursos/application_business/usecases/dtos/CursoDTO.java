package com.devvadercursos.application_business.usecases.dtos;

import com.devvadercursos.enterprise_business.entities.enuns.TematicaEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class CursoDTO implements IGenericsDTO<Long>, Serializable {

    private Long id;

    @NotNull @NotEmpty @Length(max = 150)
    private String titulo;

    @NotNull @NotEmpty @Length(max = 250)
    private String descricao;

    @NotNull
    private TematicaEnum tematica;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dataInicio;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dataFim;

    @NotNull
    private Long cliente;

    // ----- Auditoria -----
    private LocalDateTime dataHoraCadastro;
    private LocalDateTime dataHoraUltimaAtualizacao;
}
