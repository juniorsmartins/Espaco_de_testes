package io.crudcursos.cursos_contexto.dominio.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.crudcursos.cursos_contexto.dominio.entity.AssuntoEntity;
import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class AssuntoDTOResponse implements Serializable, IDTO<Long> {

    public static final long serialVersionUID = 1L;

    private Long id;
    private String tema;

    public AssuntoDTOResponse(AssuntoEntity assuntoEntity) {
        this.id = assuntoEntity.getId();
        this.tema = assuntoEntity.getTema();
    }
}
