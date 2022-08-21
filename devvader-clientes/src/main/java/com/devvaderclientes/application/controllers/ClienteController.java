package com.devvaderclientes.application.controllers;

import com.devvaderclientes.domain.dtos.request.ClienteDtoRequest;
import com.devvaderclientes.domain.ports.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/clientes")
public class ClienteController {

    @Autowired
    private IClienteService iClienteService;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid ClienteDtoRequest clienteDtoRequest) {
        return iClienteService.cadastrar(clienteDtoRequest);
    }
}
