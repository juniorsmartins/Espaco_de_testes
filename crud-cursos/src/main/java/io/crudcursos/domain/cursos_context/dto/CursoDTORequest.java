package io.crudcursos.domain.cursos_context.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.hibernate.validator.constraints.Length;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public record CursoDTORequest
        (
            Long id,

            @NotNull
            @NotEmpty
            @Length(max = 175)
            String titulo,

            @NotNull
            @NotEmpty
            @Length(max = 175)
            String instituicao,

            @NotNull
            @Positive
            Float cargaHoraria,

            @NotNull
            @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
            @JsonDeserialize(using = LocalDateDeserializer.class)
            @JsonSerialize(using = LocalDateSerializer.class)
            LocalDate dataConclusao,

            @NotNull
            @PositiveOrZero
            BigDecimal preco,

            @Length(max = 255)
            String link,

            @NotNull @Valid
            AssuntoDTORequest assunto
        ) implements Serializable, IDTO<Long>
{
    public static final long serialVersionUID = 1L;
}

