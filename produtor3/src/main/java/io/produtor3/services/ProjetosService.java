package io.produtor3.services;

import io.produtor3.dtos.ProjetoDTO;
import io.produtor3.entities.ProjetoEntity;
import io.produtor3.repositories.ProjetosRepository;
import io.produtor3.uteis.RabbitMQConstantes;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;

@Service
@Slf4j
public final class ProjetosService implements IProjetosService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProjetosRepository projetosRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public ResponseEntity<?> cadastrar(ProjetoDTO projetoDTO) {

        return Optional.of(projetoDTO)
                .map(projDTO -> modelMapper.map(projDTO, ProjetoEntity.class))
                .map(projEntity -> projetosRepository.save(projEntity))
                .map(projEntity -> modelMapper.map(projEntity, ProjetoDTO.class))
                .map(projDTO -> {
                    rabbitTemplate.convertAndSend(RabbitMQConstantes.FILA_CREATE_PROJETO, projDTO);
                    return projDTO;})
                .map(projDTO -> {
                    log.info("\n \n Projeto cadastrado: " + projDTO.getNome() + "\n");
                    return projDTO;})
                .map(projDTO -> ResponseEntity.created(URI.create("/" + projDTO.getId())).body(projDTO))
                .orElseThrow();
    }
}
