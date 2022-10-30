package io.crudcursos.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class CursoDTO implements Serializable, IDTO<Long> {

    public static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    @NotEmpty
    @Length(max = 175)
    private String titulo;

    @NotNull
    @NotEmpty
    @Length(max = 175)
    private String instituicao;

    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dataConclusao;

    @NotNull
    @Positive
    private float cargaHoraria;

    @NotNull
    @PositiveOrZero
    private BigDecimal preco;

    @Length(max = 255)
    private String link;

    @NotNull @Valid
    private AssuntoDTO assunto;
}
