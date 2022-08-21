package com.devvaderclientes.domain.services;

import com.devvaderclientes.domain.dtos.request.ClienteDtoRequest;
import com.devvaderclientes.domain.dtos.response.ClienteDtoResponse;
import com.devvaderclientes.domain.entities.ClienteEntity;
import com.devvaderclientes.domain.ports.IClienteService;
import com.devvaderclientes.resources.repositories.IClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;

@Service
public final class ClienteServiceImpl implements IClienteService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IClienteRepository iClienteRepository;

    @Override
    public ResponseEntity<?> cadastrar(ClienteDtoRequest clienteDtoRequest) {
        final var clienteDeSaida = Optional.of(clienteDtoRequest)
                .map(clienteDeEntrada -> modelMapper.map(clienteDeEntrada, ClienteEntity.class))
                .map(clienteNovo -> {
                    clienteNovo.getContatos().forEach(contatoEntity -> contatoEntity.setCliente(clienteNovo));
                    clienteNovo.getEndereco().setCliente(clienteNovo);
                    return iClienteRepository.saveAndFlush(clienteNovo);})
                .map(clienteEntity -> modelMapper.map(clienteEntity, ClienteDtoResponse.class))
                .orElseThrow();

        return ResponseEntity
                .created(URI.create("/".concat(String.valueOf(clienteDeSaida.getClienteId()))))
                .body(clienteDeSaida);
    }
}
