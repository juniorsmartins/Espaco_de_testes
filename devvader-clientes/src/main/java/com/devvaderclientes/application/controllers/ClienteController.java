package com.devvaderclientes.application.controllers;

import com.devvaderclientes.domain.dtos.request.ClienteDtoRequest;
import com.devvaderclientes.domain.ports.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/v1/clientes")
public class ClienteController {

    @Autowired
    private IClienteService iClienteService;

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody @Valid ClienteDtoRequest clienteDtoRequest) {
        return iClienteService.criar(clienteDtoRequest);
    }

    @GetMapping
    public ResponseEntity<?> ler() {
        return iClienteService.ler();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> consultarPorId(@PathVariable @NotNull Long id) {
        return iClienteService.consultarPorId(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> atualizarPorId(@PathVariable @NotNull Long id, @RequestBody @Valid ClienteDtoRequest clienteDtoRequest) {
        return iClienteService.atualizarPorId(id, clienteDtoRequest);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable @NotNull Long id) {
        return iClienteService.deletarPorId(id);
    }
}
