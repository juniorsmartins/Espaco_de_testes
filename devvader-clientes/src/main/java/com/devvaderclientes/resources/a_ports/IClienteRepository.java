package com.devvaderclientes.resources.a_ports;

import com.devvaderclientes.domain.entities.ClienteEntity;

import java.util.List;

public interface IClienteRepository {

    ClienteEntity salvarCliente(ClienteEntity clienteEntity);
    List<ClienteEntity> listarClientes();
    ClienteEntity consultarClientePorId(Long clienteId);
    void deletarCliente(ClienteEntity clienteEntity);
}
