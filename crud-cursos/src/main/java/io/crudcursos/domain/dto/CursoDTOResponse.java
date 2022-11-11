package io.crudcursos.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;


public record CursoDTOResponse(Long id, String titulo, String instituicao, Float cargaHoraria, LocalDate dataConclusao,
                   BigDecimal preco, String link, AssuntoDTOResponse assunto) implements Serializable, IDTO<Long> {}

