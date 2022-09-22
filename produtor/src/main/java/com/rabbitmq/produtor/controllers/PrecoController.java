package com.rabbitmq.produtor.controllers;

import com.rabbitmq.produtor.connection.RabbitMQConnection;
import com.rabbitmq.produtor.dtos.PrecoDTO;
import com.rabbitmq.produtor.service.RabbitMQService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/preco")
public class PrecoController {

    @Autowired
    private RabbitMQService rabbitMQService;

    @PutMapping
    private ResponseEntity alterarPreco(@RequestBody PrecoDTO precoDTO) {
        System.out.println("\n Código: " + precoDTO.codigo + " Preço:" + precoDTO.preco + "\n");
        this.rabbitMQService.enviaMensagem(RabbitMQConnection.FILA_NUM2, precoDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
}
