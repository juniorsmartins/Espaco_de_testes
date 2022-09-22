package com.rabbitmq.consumidor.rabbitmq;

import dtos.EstoqueDTO;
import dtos.PrecoDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import rabbitmq.ConstantesRabbitMQ;

@Component
public class ConsumerService {

    @RabbitListener(queues = ConstantesRabbitMQ.FILA_NUM1)
    private void consumidor(EstoqueDTO estoqueDTO){
        System.out.println("\nConsumidor - codigo: " + estoqueDTO.codigo + " quantidade: " + estoqueDTO.quantidade + "\n");
    }

    @RabbitListener(queues = ConstantesRabbitMQ.FILA_NUM2)
    private void consumidor(PrecoDTO precoDTO){
        System.out.println("\nConsumidor - codigo: " + precoDTO.codigo + " preço: " + precoDTO.preco + "\n");
    }
}
