package io.produtor5.controllers;

import io.produtor5.config.RabbitMQConstantes;
import io.produtor5.entities.Mensagem;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping(value = "/v2")
    public Mensagem createMensagem(@RequestBody Mensagem mensagem) {
        rabbitTemplate.convertAndSend(RabbitMQConstantes.FILA_CREATE_MENSAGEM2, mensagem);
        log.info("\n" + mensagem);
        return mensagem;
    }
}
