package com.devvaderclientes.domain.ports;

import com.devvaderclientes.domain.dtos.request.ClienteDtoRequest;
import org.springframework.http.ResponseEntity;

public interface IClienteService {

    ResponseEntity<?> criar(ClienteDtoRequest clienteDtoRequest);
    ResponseEntity<?> ler();
    ResponseEntity<?> consultarDetalhadoPorId(Long id);
    ResponseEntity<?> atualizarPorId(Long id, ClienteDtoRequest clienteDtoRequest);
    ResponseEntity<?> deletarPorId(Long id);
}
