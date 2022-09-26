package com.produtor2.service;

import com.produtor2.dto.CarroDTO;
import com.produtor2.entities.Carro;
import com.produtor2.rabbitmq.ConnectionRabbitMQConfig;
import com.produtor2.repository.CarroRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.Optional;

public class CarroService {

    public static final String FILA_CREATE_CARRO = "FILA_CREATE_CARRO";

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public ResponseEntity<?> cadastrar(CarroDTO carroDTO) {
        return Optional.of(carroDTO)
                .map(carDTO -> new Carro(carDTO))
                .map(car -> carroRepository.save(car))
                .map(car -> {rabbitTemplate.convertAndSend(ConnectionRabbitMQConfig.NOME_FILA_CREATE, car); return car;})
                .map(car -> ResponseEntity.created(URI.create("/" + car.getId())).body(new CarroDTO(car)))
                .get();
    }
}
