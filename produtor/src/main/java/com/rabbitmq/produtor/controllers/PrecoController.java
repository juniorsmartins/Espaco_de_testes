package com.rabbitmq.produtor.controllers;

import com.rabbitmq.produtor.service.RabbitMQService;
import dtos.PrecoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rabbitmq.ConstantesRabbitMQ;

@RestController
@RequestMapping(value = "/preco")
public class PrecoController {

    @Autowired
    private RabbitMQService rabbitMQService;

    @PutMapping
    private ResponseEntity alterarPreco(@RequestBody PrecoDTO precoDTO) {
        System.out.println("\n Código: " + precoDTO.codigo + " Preço:" + precoDTO.preco + "\n");
        this.rabbitMQService.enviaMensagem(ConstantesRabbitMQ.FILA_NUM2, precoDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
}
