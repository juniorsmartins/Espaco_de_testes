package com.devvaderclientes.domain.ports;

import com.devvaderclientes.domain.dtos.request.ClienteDtoRequest;
import com.devvaderclientes.domain.dtos.response.ClienteDtoResponse;
import org.springframework.http.ResponseEntity;

public interface IClienteService {

    ResponseEntity<?> cadastrar(ClienteDtoRequest clienteDtoRequest);
    ResponseEntity<?> lerTodos();
    ResponseEntity<?> consultarPorId(Long id);
    ResponseEntity<?> atualizarPorId(Long id, ClienteDtoRequest clienteDtoRequest);
}
