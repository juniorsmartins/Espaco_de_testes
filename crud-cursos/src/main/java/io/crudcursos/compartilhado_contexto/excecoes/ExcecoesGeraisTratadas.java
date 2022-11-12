package io.crudcursos.compartilhado_contexto.excecoes;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ExcecoesGeraisTratadas {

    private String status;
    private String mensagem;

    public ExcecoesGeraisTratadas(String status, String mensagem) {
        this.status = status;
        this.mensagem = mensagem;
    }
}
