package io.produtor4.controllers;

import io.produtor4.config.RabbitMQConstantes;
import io.produtor4.entities.Mensagem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/mensagens")
@Slf4j
public class MensagemController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    public Mensagem create(@RequestBody Mensagem mensagem) {
        Message message = new Message(mensagem.getAssunto().getBytes());
        rabbitTemplate.send(RabbitMQConstantes.FILA_CREATE_MENSAGEM, message);
        log.info("\n\n" + mensagem + "\n");
        return mensagem;
    }

    @PostMapping(value = "/v2")
    public Mensagem create2(@RequestBody Mensagem mensagem) {
        Message message = new Message(mensagem.getAssunto().getBytes());
        rabbitTemplate.send(RabbitMQConstantes.FILA_CREATE_MENSAGEM, message);
        log.info("\n\n" + mensagem + "\n");
        return mensagem;
    }

//    @PostMapping
//    public Mensagem create(@RequestBody Mensagem mensagem) {
//        rabbitTemplate.convertAndSend(RabbitMQConstantes.FILA_CREATE_MENSAGEM, mensagem);
//        log.info("\n\n" + mensagem + "\n");
//        return mensagem;
//    }
}
