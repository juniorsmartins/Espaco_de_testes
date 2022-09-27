package io.produtor3.services;

import io.produtor3.dtos.ProjetoDTO;
import org.springframework.http.ResponseEntity;

public interface IProjetosService {

    ResponseEntity<?> cadastrar(ProjetoDTO projetoDTO);
}
