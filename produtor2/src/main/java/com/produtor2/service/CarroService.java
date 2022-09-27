package com.produtor2.service;

import com.produtor2.entities.Carro;
import com.produtor2.repository.CarroRepository;
import com.produtor2.repository.CarroTempRepository;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import rabbitmq.CarroDTO;
import rabbitmq.ConstantesRabbitMQ;

import java.net.URI;
import java.util.Optional;

@Service
public final class CarroService {

    public static final String FILA_CREATE_CARRO = "FILA_CREATE_CARRO";

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private CarroTempRepository carroTempRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public ResponseEntity<?> cadastrar(CarroDTO carroDTO) {
        return Optional.of(carroDTO)
                .map(carDTO -> modelMapper.map(carDTO, Carro.class))
                .map(carEntity -> carroRepository.save(carEntity))
                .map(carEntity -> modelMapper.map(carEntity, CarroDTO.class))
                .map(carDTO -> {
                    rabbitTemplate.convertAndSend(ConstantesRabbitMQ.NOME_FILA_CREATE, carDTO);
                    return carDTO;})
                .map(carDTO -> ResponseEntity.created(URI.create("/" + carDTO.getId())).body(carDTO))
                .orElseThrow();
    }
}
