package io.produtor3.services;

import io.produtor3.dtos.ProjetoDTO;
import io.produtor3.entities.ProjetoEntity;
import io.produtor3.repositories.ProjetosRepository;
import io.produtor3.uteis.RabbitMQConstantes;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;

@Service
public class ProjetosService implements IProjetosService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProjetosRepository projetosRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public ResponseEntity<?> cadastrar(ProjetoDTO projetoDTO) {
        return Optional.of(projetoDTO)
                .map(projDTO -> modelMapper.map(projDTO, ProjetoEntity.class))
                .map(projEntity -> projetosRepository.save(projEntity))
                .map(projEntity -> modelMapper.map(projEntity, ProjetoDTO.class))
                .map(projDTO -> {
                    amqpTemplate.convertAndSend(RabbitMQConstantes.FILA_CREATE_PROJETO, projDTO);
                    return projDTO;})
                .map(projDTO -> ResponseEntity.created(URI.create("/" + projDTO.getId())).body(projDTO))
                .orElseThrow();
    }
}
