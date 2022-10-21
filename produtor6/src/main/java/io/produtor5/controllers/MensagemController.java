package io.produtor5.controllers;

import io.produtor5.config.RabbitMQConfig;
import io.produtor5.entities.Mensagem;
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

    @PostMapping(value = "/v1")
    public Mensagem createMensagemSimples(@RequestBody Mensagem mensagem) {
        Message message = new Message(mensagem.getAssunto().getBytes());
        rabbitTemplate.send(RabbitMQConfig.FILA_MENSAGENS_SIMPLES, message);
        log.info("\n\nObjeto Simples: \n" + mensagem);
        return mensagem;
    }

    @PostMapping(value = "/v2")
    public Mensagem createMensagemComplexa(@RequestBody Mensagem mensagem) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.FILA_MENSAGENS_COMPLEXAS, mensagem);
        log.info("\n\nObjeto Complexo: \n" + mensagem);
        return mensagem;
    }
}
