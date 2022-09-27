package io.produtor3.dtos;

import io.produtor3.entities.enuns.DatabaseEnum;
import io.produtor3.entities.enuns.FrameworkEnum;
import io.produtor3.entities.enuns.LinguagemEnum;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public final class ProjetoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull @NotEmpty
    private String nome;

    @NotNull @NotEmpty
    @Length(min = 8)
    private String descricao;

    @NotBlank
    private LinguagemEnum linguagem;

    @NotBlank
    private FrameworkEnum framework;

    @NotBlank
    private DatabaseEnum database;
}
