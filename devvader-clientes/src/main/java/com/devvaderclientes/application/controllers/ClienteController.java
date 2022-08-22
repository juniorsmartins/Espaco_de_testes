package com.devvaderclientes.application.controllers;

import com.devvaderclientes.domain.dtos.request.ClienteDtoRequest;
import com.devvaderclientes.domain.ports.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/clientes")
public class ClienteController {

    @Autowired
    private IClienteService iClienteService;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid ClienteDtoRequest clienteDtoRequest) {
        return iClienteService.cadastrar(clienteDtoRequest);
    }

    @GetMapping
    public ResponseEntity<?> lerTodos() {
        return iClienteService.lerTodos();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> consultarPorId(@PathVariable Long id) {
        return iClienteService.consultarPorId(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> atualizarPorId(@PathVariable Long id, @RequestBody @Valid ClienteDtoRequest clienteDtoRequest) {
        return iClienteService.atualizarPorId(id, clienteDtoRequest);
    }
}
