package com.rabbitmq.produtor.controllers;

import com.rabbitmq.produtor.service.RabbitMQService;
import dtos.EstoqueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rabbitmq.ConstantesRabbitMQ;

@RestController
@RequestMapping(value = "/estoque")
public class EstoqueController {

    @Autowired
    private RabbitMQService rabbitMQService;

    @PutMapping
    private ResponseEntity alterarEstoque(@RequestBody EstoqueDTO estoqueDTO) {
        System.out.println("\n Código: " + estoqueDTO.codigo + " Quantidade: " + estoqueDTO.quantidade + "\n");
        this.rabbitMQService.enviaMensagem(ConstantesRabbitMQ.FILA_NUM1, estoqueDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
}
