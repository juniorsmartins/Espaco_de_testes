package io.consumidor4.listener;

import io.consumidor4.config.RabbitMQConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ConsumidorListener {

    @RabbitListener(queues = RabbitMQConfig.FILA_MENSAGENS_SIMPLES)
    public void receberMensagemSimples(@Payload Message message) {
        System.out.println("\n" + message);
    }
}
