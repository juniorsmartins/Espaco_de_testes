package com.consumidor2.rabbitmq.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import rabbitmq.CarroDTO;
import rabbitmq.ConstantesRabbitMQ;

@Component
public class ConsumerService {

    @RabbitListener(queues = ConstantesRabbitMQ.NOME_FILA_CREATE)
    private void consumidor(CarroDTO carroDTO) {
        System.out.println("\n Marca: " + carroDTO.getMarca());
    }
}
