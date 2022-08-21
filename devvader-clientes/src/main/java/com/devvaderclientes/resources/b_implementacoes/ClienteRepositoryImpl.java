package com.devvaderclientes.resources.b_implementacoes;

import com.devvaderclientes.domain.entities.ClienteEntity;
import com.devvaderclientes.resources.a_ports.IClienteRepository;
import com.devvaderclientes.resources.c_repositories.ClienteRepository;
import com.devvaderclientes.resources.c_repositories.ContatoRepository;
import com.devvaderclientes.resources.c_repositories.EnderecoRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Builder
@Getter
@Setter
public final class ClienteRepositoryImpl implements IClienteRepository {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Override
    public ClienteEntity salvarCliente(ClienteEntity clienteEntity) {
        return null;
    }

    @Override
    public List<ClienteEntity> listarClientes() {
        return null;
    }

    @Override
    public ClienteEntity consultarClientePorId(Long clienteId) {
        return null;
    }

    @Override
    public void deletarCliente(ClienteEntity clienteEntity) {

    }
}
