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
    public Mensagem createString(@RequestBody Mensagem mensagem) {
        Message message = new Message(mensagem.getAssunto().toString().getBytes());
        rabbitTemplate.send(RabbitMQConstantes.FILA_CREATE_MENSAGEM, message);
        log.info("\n\n" + mensagem.getAssunto() + "\n" + mensagem.getDescricao() + "\n");
        return mensagem;
    }
}
