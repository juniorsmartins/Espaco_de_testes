package io.crudcursos.domain.dto;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

public record AssuntoDTOResponse(Long id, String tema) implements Serializable, IDTO<Long> {}
