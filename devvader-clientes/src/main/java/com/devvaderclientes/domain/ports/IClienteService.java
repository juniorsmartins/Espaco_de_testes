package com.devvaderclientes.domain.ports;

import com.devvaderclientes.domain.dtos.request.ClienteDtoRequest;
import org.springframework.http.ResponseEntity;

public interface IClienteService {

    ResponseEntity<?> cadastrar(ClienteDtoRequest clienteDtoRequest);
}
