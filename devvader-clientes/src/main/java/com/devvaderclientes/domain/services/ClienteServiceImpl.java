package com.devvaderclientes.domain.services;

import com.devvaderclientes.domain.dtos.request.ClienteDtoRequest;
import com.devvaderclientes.domain.dtos.response.ClienteDtoResponse;
import com.devvaderclientes.domain.entities.ClienteEntity;
import com.devvaderclientes.domain.exceptions.MensagemPadronizada;
import com.devvaderclientes.domain.exceptions.RecursoNaoEncontradoException;
import com.devvaderclientes.domain.ports.IClienteService;
import com.devvaderclientes.infra.repositories.IClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Comparator;
import java.util.Optional;

@Service
public final class ClienteServiceImpl implements IClienteService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IClienteRepository iClienteRepository;

    @Override
    public ResponseEntity<?> criar(ClienteDtoRequest clienteDtoRequest) {
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

    @Override
    public ResponseEntity<?> ler() {
        return ResponseEntity
                .ok()
                .body(iClienteRepository
                        .findAll()
                        .stream()
                        .map(clienteEntity -> modelMapper.map(clienteEntity, ClienteDtoResponse.class))
                        .sorted(Comparator.comparing(ClienteDtoResponse::getClienteId).reversed())
                        .toList()
                );
    }

    @Override
    public ResponseEntity<?> consultarPorId(Long id) {
        return ResponseEntity
                .ok()
                .body(iClienteRepository
                        .findById(id)
                        .map(clienteEntity -> modelMapper.map(clienteEntity, ClienteDtoResponse.class))
                        .orElseThrow(() -> new RecursoNaoEncontradoException(MensagemPadronizada.RECURSO_NAO_ENCONTRADO))
                );
    }

    @Override
    public ResponseEntity<?> atualizarPorId(Long id, ClienteDtoRequest clienteDtoRequest) {
        return ResponseEntity
                .ok()
                .body(iClienteRepository.findById(id)
                        .map(clienteEntity -> {
                            var clienteAtual = modelMapper.map(clienteDtoRequest, ClienteEntity.class);
                            clienteAtual.setClienteId(clienteEntity.getClienteId());
                            clienteAtual.getEndereco().setEnderecoId(clienteEntity.getEndereco().getEnderecoId());
                            clienteAtual.getEndereco().setCliente(clienteAtual);
                            clienteEntity.getContatos().forEach(contatoEntity -> clienteEntity.getContatos().remove(contatoEntity));
                            clienteAtual.getContatos().forEach(contatoEntity -> contatoEntity.setCliente(clienteAtual));
                            return iClienteRepository.saveAndFlush(clienteAtual);
                        }).map(clienteEntity -> modelMapper.map(clienteEntity, ClienteDtoResponse.class))
                        .orElseThrow(() -> new RecursoNaoEncontradoException(MensagemPadronizada.RECURSO_NAO_ENCONTRADO))
                );
    }

    @Override
    public ResponseEntity<?> deletarPorId(Long id) {
        return ResponseEntity
                .ok()
                .body(iClienteRepository
                        .findById(id)
                        .map(clienteEntity -> {
                            iClienteRepository.delete(clienteEntity);
                            return iClienteRepository
                                    .findAll()
                                    .stream()
                                    .map(cliente -> modelMapper.map(cliente, ClienteDtoResponse.class))
                                    .sorted(Comparator.comparing(ClienteDtoResponse::getClienteId).reversed())
                                    .toList();
                        })
                        .orElseThrow(() -> new RecursoNaoEncontradoException(MensagemPadronizada.RECURSO_NAO_ENCONTRADO))
                );
    }
}
