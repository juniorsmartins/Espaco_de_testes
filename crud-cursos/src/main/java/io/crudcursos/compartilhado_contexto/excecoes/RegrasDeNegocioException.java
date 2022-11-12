package io.crudcursos.compartilhado_contexto.excecoes;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class RegrasDeNegocioException extends RuntimeException {

    public RegrasDeNegocioException(String mensagem) {
        super(mensagem);
    }
}
