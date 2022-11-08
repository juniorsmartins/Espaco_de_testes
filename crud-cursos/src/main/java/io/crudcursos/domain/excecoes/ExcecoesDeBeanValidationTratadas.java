package io.crudcursos.domain.excecoes;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ExcecoesDeBeanValidationTratadas {

    private String status;
    private String mensagem;
    private String anotacao;
    private String nomeDoCampo;

    public ExcecoesDeBeanValidationTratadas(String status, String mensagem) {
        this.status = status;
        this.mensagem = mensagem;
    }

    public ExcecoesDeBeanValidationTratadas(String status, String mensagem, String anotacao, String nomeDoCampo) {
        this(status, mensagem);
        this.anotacao = anotacao;
        this.nomeDoCampo = nomeDoCampo;
    }
}
